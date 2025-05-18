package com.bancx.assignment.loanpaymentsystem.payment;

import com.bancx.assignment.loanpaymentsystem.exception.ResourceNotFoundException;
import com.bancx.assignment.loanpaymentsystem.loan.model.Loan;
import com.bancx.assignment.loanpaymentsystem.loan.model.LoanStatus;
import com.bancx.assignment.loanpaymentsystem.loan.repository.LoanRepository;
import com.bancx.assignment.loanpaymentsystem.payment.constants.PaymentMessageConstants;
import com.bancx.assignment.loanpaymentsystem.payment.dto.PaymentRequestDto;
import com.bancx.assignment.loanpaymentsystem.payment.dto.PaymentResponseDto;
import com.bancx.assignment.loanpaymentsystem.payment.model.Payment;
import com.bancx.assignment.loanpaymentsystem.payment.repository.PaymentRepository;
import com.bancx.assignment.loanpaymentsystem.payment.service.impl.PaymentServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link PaymentServiceImpl} class.
 *
 * This class verifies payment recording logic, such as reducing loan balance,
 * handling overpayment scenarios, and updating loan status to SETTLED upon full repayment.
 * It uses Mockito to mock the dependencies {@link LoanRepository} and {@link PaymentRepository}.
 */
@SpringBootTest
public class PaymentServiceImplTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentServiceImpl;

    /**
     * Tests that a valid payment reduces the loan's remaining balance accordingly
     * and keeps the loan in ACTIVE status if it's not fully paid off.
     */
    @Test
    void testSuccessfulPaymentReducesLoanBalance() {
        // Given: a loan with an initial balance
        Loan loan = new Loan();
        loan.setLoanId(1L);
        loan.setLoanAmount(new BigDecimal("1000.00"));
        loan.setRemainingBalance(new BigDecimal("1000.00"));
        loan.setStatus(LoanStatus.ACTIVE);

        PaymentRequestDto dto = new PaymentRequestDto(1L, new BigDecimal("200.00"));

        Payment payment = new Payment();
        payment.setPaymentId(10L);
        payment.setLoan(loan);
        payment.setPaymentAmount(dto.getPaymentAmount());
        payment.setPaymentDate(LocalDate.now());

        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        // When: a payment is made
        PaymentResponseDto result = paymentServiceImpl.recordPayment(dto);

        // Then: the loan balance should be reduced
        assertNotNull(result);
        assertEquals(new BigDecimal("800.00"), result.getNewRemainingBalance());
        assertEquals("ACTIVE", result.getLoanStatus());
        assertEquals(dto.getPaymentAmount(), result.getPaymentAmount());
    }

    /**
     * Tests that an overpayment (i.e., payment amount greater than the remaining loan balance)
     * throws an {@link IllegalArgumentException} with the appropriate error message.
     */
    @Test
    void testOverpaymentThrowsError() {
        Loan loan = new Loan();
        loan.setLoanId(1L);
        loan.setRemainingBalance(new BigDecimal("100.00"));

        PaymentRequestDto dto = new PaymentRequestDto(1L, new BigDecimal("200.00"));

        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> paymentServiceImpl.recordPayment(dto)
        );

        assertEquals(PaymentMessageConstants.PAYMENT_EXCEEDS_REMAINING, exception.getMessage());
    }

    /**
     * Tests that when the payment amount exactly matches the remaining loan balance,
     * the loan status is updated to {@link LoanStatus#SETTLED}.
     */
    @Test
    void testLoanMovesToSettledAfterFullPayment() {
        Loan loan = new Loan();
        loan.setLoanId(2L);
        loan.setRemainingBalance(new BigDecimal("100.00"));
        loan.setStatus(LoanStatus.ACTIVE);

        PaymentRequestDto dto = new PaymentRequestDto(2L, new BigDecimal("100.00"));

        Payment expectedPayment = new Payment(
                1L, // paymentId (mocked)
                loan,
                dto.getPaymentAmount(),
                LocalDate.now() // you can mock the date if needed
        );

        when(loanRepository.findById(2L)).thenReturn(Optional.of(loan));
        when(paymentRepository.save(any(Payment.class))).thenReturn(expectedPayment);
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        paymentServiceImpl.recordPayment(dto);

        assertEquals(0, loan.getRemainingBalance().compareTo(BigDecimal.ZERO));
        assertEquals(LoanStatus.SETTLED, loan.getStatus());
    }

    /**
     * Tests that a payment made against a loan that is already in {@link LoanStatus#SETTLED}
     * is rejected with an {@link IllegalArgumentException}.
     *
     * <p>
     * This test ensures that:
     * <ul>
     *   <li>No payment can be made toward a loan with a zero remaining balance.</li>
     *   <li>The service throws an exception with the appropriate error message defined in
     *       {@link com.bancx.assignment.loanpaymentsystem.payment.constants.PaymentMessageConstants#PAYMENT_EXCEEDS_REMAINING}.</li>
     * </ul>
     * </p>
     *
     * <p>
     * This is a negative test case and confirms that the system does not allow
     * invalid financial operations on settled loans.
     * </p>
     */
    @Test
    void testPaymentRejectedForSettledLoan() {
        Loan loan = new Loan();
        loan.setLoanId(3L);
        loan.setRemainingBalance(BigDecimal.ZERO);
        loan.setStatus(LoanStatus.SETTLED);

        PaymentRequestDto dto = new PaymentRequestDto(3L, new BigDecimal("50.00"));

        when(loanRepository.findById(3L)).thenReturn(Optional.of(loan));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> paymentServiceImpl.recordPayment(dto)
        );

        assertEquals(PaymentMessageConstants.PAYMENT_EXCEEDS_REMAINING, exception.getMessage());
    }

    /**
     * Tests that an overpayment attempt does not result in any changes to the system.
     *
     * <p>This negative test case ensures that when a payment amount exceeds the loan's remaining balance:</p>
     * <ul>
     *   <li>An {@link IllegalArgumentException} is thrown.</li>
     *   <li>No payment is persisted to the {@link com.bancx.assignment.loanpaymentsystem.payment.repository.PaymentRepository}.</li>
     *   <li>The loan is not updated or saved through the {@link com.bancx.assignment.loanpaymentsystem.loan.repository.LoanRepository}.</li>
     * </ul>
     *
     * <p>This test protects the system from processing invalid overpayment scenarios and confirms that
     * neither side effects nor unintended database operations occur.</p>
     */
    @Test
    void testOverpaymentDoesNotSavePaymentOrLoan() {
        Loan loan = new Loan();
        loan.setLoanId(1L);
        loan.setRemainingBalance(new BigDecimal("100.00"));

        PaymentRequestDto dto = new PaymentRequestDto(1L, new BigDecimal("150.00"));

        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));

        assertThrows(IllegalArgumentException.class, () -> paymentServiceImpl.recordPayment(dto));

        verify(paymentRepository, never()).save(any(Payment.class));
        verify(loanRepository, never()).save(any(Loan.class));
    }

    /**
     * Tests that a payment attempt fails when the specified loan cannot be found.
     *
     * <p>This test ensures that the {@code recordPayment} method throws a
     * {@link com.bancx.assignment.loanpaymentsystem.exception.ResourceNotFoundException} when:</p>
     * <ul>
     *   <li>The loan ID provided in the {@link PaymentRequestDto} does not exist in the system.</li>
     * <li>Returns an empty {@link java.util.Optional} if no loan is found using {@code LoanRepository.findById(Long)}</li>
     * </ul>
     *
     * <p>It also verifies that the exception message matches the expected constant
     * {@link com.bancx.assignment.loanpaymentsystem.payment.constants.PaymentMessageConstants#LOAN_NOT_FOUND}.</p>
     */
    @Test
    void testPaymentFailsWhenLoanNotFound() {
        PaymentRequestDto dto = new PaymentRequestDto(999L, new BigDecimal("100.00"));

        when(loanRepository.findById(999L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> paymentServiceImpl.recordPayment(dto)
        );

        assertEquals(PaymentMessageConstants.LOAN_NOT_FOUND, exception.getMessage());
    }

}
