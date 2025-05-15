package com.bancx.assignment.loanpaymentsystem.payment.service.impl;

import com.bancx.assignment.loanpaymentsystem.exception.ResourceNotFoundException;
import com.bancx.assignment.loanpaymentsystem.loan.model.Loan;
import com.bancx.assignment.loanpaymentsystem.loan.model.LoanStatus;
import com.bancx.assignment.loanpaymentsystem.loan.repository.LoanRepository;
import com.bancx.assignment.loanpaymentsystem.payment.constants.PaymentMessageConstants;
import com.bancx.assignment.loanpaymentsystem.payment.dto.PaymentRequestDto;
import com.bancx.assignment.loanpaymentsystem.payment.dto.PaymentResponseDto;
import com.bancx.assignment.loanpaymentsystem.payment.model.Payment;
import com.bancx.assignment.loanpaymentsystem.payment.repository.PaymentRepository;
import com.bancx.assignment.loanpaymentsystem.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentResponseDto recordPayment(PaymentRequestDto dto) {
        Loan loan = loanRepository.findById(dto.getLoanId())
                .orElseThrow(() -> new ResourceNotFoundException(PaymentMessageConstants.LOAN_NOT_FOUND));

        if (dto.getPaymentAmount().compareTo(loan.getRemainingBalance()) > 0) {
            throw new IllegalArgumentException(PaymentMessageConstants.PAYMENT_EXCEEDS_REMAINING);
        }

        loan.setRemainingBalance(loan.getRemainingBalance().subtract(dto.getPaymentAmount()));

        if (loan.getRemainingBalance().compareTo(BigDecimal.ZERO) == 0) {
            loan.setStatus(LoanStatus.SETTLED);
        }

        loanRepository.save(loan);

        Payment payment = new Payment();
        payment.setLoan(loan);
        payment.setPaymentAmount(dto.getPaymentAmount());
        payment.setPaymentDate(LocalDate.now());

        paymentRepository.save(payment);

        return new PaymentResponseDto(
                payment.getPaymentAmount(),
                payment.getPaymentDate(),
                loan.getRemainingBalance(),
                loan.getStatus().toString()
        );
    }

}

