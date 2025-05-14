package com.bancx.assignment.loanpaymentsystem.payment.service;

import com.bancx.assignment.loanpaymentsystem.exception.ResourceNotFoundException;
import com.bancx.assignment.loanpaymentsystem.loan.model.Loan;
import com.bancx.assignment.loanpaymentsystem.loan.model.LoanStatus;
import com.bancx.assignment.loanpaymentsystem.loan.repository.LoanRepository;
import com.bancx.assignment.loanpaymentsystem.payment.dto.PaymentRequestDto;
import com.bancx.assignment.loanpaymentsystem.payment.model.Payment;
import com.bancx.assignment.loanpaymentsystem.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment recordPayment(PaymentRequestDto dto) {
        Loan loan = loanRepository.findById(dto.getLoanId())
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));

        if (loan.getStatus() == LoanStatus.SETTLED) {
            throw new IllegalArgumentException("Loan already settled.");
        }

        BigDecimal newBalance = loan.getRemainingBalance().subtract(dto.getPaymentAmount());
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Payment exceeds remaining balance.");
        }

        loan.setRemainingBalance(newBalance);
        if (newBalance.compareTo(BigDecimal.ZERO) == 0) {
            loan.setStatus(LoanStatus.SETTLED);
        }

        Payment payment = new Payment();
        payment.setLoan(loan);
        payment.setPaymentAmount(dto.getPaymentAmount());

        loanRepository.save(loan);
        return paymentRepository.save(payment);
    }
}

