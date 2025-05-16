package com.bancx.assignment.loanpaymentsystem.payment.dto;

import com.bancx.assignment.loanpaymentsystem.payment.constants.PaymentMessageConstants;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * PaymentRequestDto is a Data Transfer Object used to encapsulate payment data
 * when making a payment towards a loan.
 * <p>
 * It includes the loan ID and the payment amount, with validation annotations
 * to ensure that the payment amount is positive and the loan ID is provided.
 */
public class PaymentRequestDto {

    /** The ID of the loan to which the payment is being applied. */
    @NotNull
    private Long loanId;

    /** The amount of the payment. Must be greater than or equal to 0.01. */
    @NotNull
    @DecimalMin(value = "0.01", message = PaymentMessageConstants.PAYMENT_MUST_BE_POSITIVE)
    private BigDecimal paymentAmount;

    public PaymentRequestDto() {}

    /**
     * Constructs a PaymentRequestDto with the specified loan ID and payment amount.
     *
     * @param loanId        the loan ID
     * @param paymentAmount the payment amount
     */
    public PaymentRequestDto(Long loanId, BigDecimal paymentAmount) {
        this.loanId = loanId;
        this.paymentAmount = paymentAmount;
    }

    /**
     * Returns the loan ID.
     *
     * @return the loan ID
     */
    public Long getLoanId() {
        return loanId;
    }

    /**
     * Sets the loan ID.
     *
     * @param loanId the loan ID to set
     */
    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    /**
     * Returns the payment amount.
     *
     * @return the payment amount
     */
    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Sets the payment amount.
     *
     * @param paymentAmount the payment amount to set
     */
    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
