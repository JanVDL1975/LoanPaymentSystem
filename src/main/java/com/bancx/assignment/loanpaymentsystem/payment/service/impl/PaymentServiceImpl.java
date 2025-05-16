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

/**
 * Implementation of the {@link PaymentService} interface that contains
 * the business logic for recording payments towards loans.
 * <p>
 * This service verifies payment validity, updates the loan's remaining balance
 * and status, persists the payment and loan updates, and returns payment details.
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * Records a payment for a loan based on the given payment request.
     * <p>
     * Validates that the loan exists and that the payment amount does not exceed
     * the remaining balance. Updates the loan's remaining balance and status,
     * saves the payment record, and returns details of the processed payment.
     *
     * @param dto the payment request data containing loan ID and payment amount
     * @return a {@link PaymentResponseDto} containing the recorded payment details
     * @throws ResourceNotFoundException if the loan is not found by the given ID
     * @throws IllegalArgumentException  if the payment amount exceeds the remaining balance
     */
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

