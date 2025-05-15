package com.bancx.assignment.loanpaymentsystem.loan;

import com.bancx.assignment.loanpaymentsystem.loan.model.Loan;
import com.bancx.assignment.loanpaymentsystem.loan.model.LoanStatus;
import com.bancx.assignment.loanpaymentsystem.loan.repository.LoanRepository;
import com.bancx.assignment.loanpaymentsystem.loan.service.LoanService;
import com.bancx.assignment.loanpaymentsystem.loan.dto.LoanRequestDto;

import com.bancx.assignment.loanpaymentsystem.payment.model.Payment;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
public class LoanTesting {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;

    @Test
    void testLoanCreationSuccessfully() {
        LoanRequestDto dto = new LoanRequestDto();
        dto.setLoanAmount(new BigDecimal("1000.00"));
        dto.setTerm(12);

        Loan loan = new Loan();
        loan.setLoanAmount(dto.getLoanAmount());
        loan.setTerm(dto.getTerm());
        loan.setRemainingBalance(dto.getLoanAmount());
        loan.setStatus(LoanStatus.ACTIVE);

        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        Loan saved = loanService.createLoan(dto);

        assertNotNull(saved);
        assertEquals(new BigDecimal("1000.00"), saved.getRemainingBalance());
        assertEquals(LoanStatus.ACTIVE, saved.getStatus());
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    void testLoanSettlement() {
        Loan loan = new Loan();
        loan.setLoanId(1L);
        loan.setLoanAmount(new BigDecimal("500.00"));
        loan.setRemainingBalance(BigDecimal.ZERO);
        loan.setStatus(LoanStatus.SETTLED);

        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));

        Loan retrieved = loanService.getLoanDetails(1L);
        assertEquals(LoanStatus.SETTLED, retrieved.getStatus());
    }
/*
    @Test
    void testLoanSettlementAfterFullPayment() {
        // Arrange
        Loan loan = new Loan();
        loan.setLoanId(1L);
        loan.setLoanAmount(new BigDecimal("1000.00"));
        loan.setRemainingBalance(new BigDecimal("1000.00"));
        loan.setStatus(LoanStatus.ACTIVE);

        Payment payment = new Payment();
        payment.setLoanId(1L);
        payment.setPaymentAmount(new BigDecimal("1000.00"));

        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        when(loanRepository.save(any(Loan.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        paymentService.makePayment(payment); // Your real method that handles the payment

        // Assert
        assertEquals(BigDecimal.ZERO, loan.getRemainingBalance());
        assertEquals(LoanStatus.SETTLED, loan.getStatus());
    }
*/
}
