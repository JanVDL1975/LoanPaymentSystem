package com.bancx.assignment.loanpaymentsystem.payment;

import com.bancx.assignment.loanpaymentsystem.loan.model.Loan;
import com.bancx.assignment.loanpaymentsystem.loan.model.LoanStatus;
import com.bancx.assignment.loanpaymentsystem.loan.repository.LoanRepository;
import com.bancx.assignment.loanpaymentsystem.payment.dto.PaymentRequestDto;
import com.bancx.assignment.loanpaymentsystem.payment.model.Payment;
import com.bancx.assignment.loanpaymentsystem.payment.repository.PaymentRepository;
import com.bancx.assignment.loanpaymentsystem.payment.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PaymentServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    void testSuccessfulPaymentReducesLoanBalance() {
        Loan loan = new Loan();
        loan.setLoanId(1L);
        loan.setLoanAmount(new BigDecimal("1000.00"));
        loan.setRemainingBalance(new BigDecimal("1000.00"));
        loan.setStatus(LoanStatus.ACTIVE);

        PaymentRequestDto dto = new PaymentRequestDto(1L, new BigDecimal("200.00"));
        Payment expectedPayment = new Payment();
        expectedPayment.setPaymentAmount(dto.getPaymentAmount());

        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        when(paymentRepository.save(any(Payment.class))).thenReturn(expectedPayment);
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        Payment result = paymentService.recordPayment(dto);

        assertNotNull(result);
        assertEquals(new BigDecimal("800.00"), loan.getRemainingBalance());
        assertEquals(LoanStatus.ACTIVE, loan.getStatus());
    }

    @Test
    void testOverpaymentThrowsError() {
        Loan loan = new Loan();
        loan.setLoanId(1L);
        loan.setRemainingBalance(new BigDecimal("100.00"));

        PaymentRequestDto dto = new PaymentRequestDto(1L, new BigDecimal("200.00"));

        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> paymentService.recordPayment(dto)
        );

        assertEquals("Payment exceeds the remaining balance", exception.getMessage());
    }

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

        paymentService.recordPayment(dto);

        assertEquals(0, loan.getRemainingBalance().compareTo(BigDecimal.ZERO));
        assertEquals(LoanStatus.SETTLED, loan.getStatus());
    }

}
