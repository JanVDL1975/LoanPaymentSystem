package com.bancx.assignment.loanpaymentsystem.payment.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * PaymentResponseDto is a Data Transfer Object used to return details
 * about a payment made towards a loan.
 * <p>
 * It contains the payment amount, the date the payment was made,
 * the updated remaining balance of the loan, and the current status of the loan.
 */
public class PaymentResponseDto {
    /** The amount paid in the payment. */
    private BigDecimal paymentAmount;

    /** The date when the payment was made. */
    private LocalDate paymentDate;

    /** The remaining balance of the loan after the payment. */
    private BigDecimal newRemainingBalance;

    /** The current status of the loan, e.g., ACTIVE or SETTLED. */
    private String loanStatus;

    public PaymentResponseDto() {}

    /**
     * Constructs a PaymentResponseDto with all relevant payment details.
     *
     * @param paymentAmount       the amount paid
     * @param paymentDate         the date the payment was made
     * @param newRemainingBalance the remaining balance after the payment
     * @param loanStatus          the current status of the loan
     */
    public PaymentResponseDto(BigDecimal paymentAmount, LocalDate paymentDate,
                              BigDecimal newRemainingBalance, String loanStatus) {
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.newRemainingBalance = newRemainingBalance;
        this.loanStatus = loanStatus;
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
     * @param paymentAmount the amount paid
     */
    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    /**
     * Returns the date the payment was made.
     *
     * @return the payment date
     */
    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    /**
     * Sets the date the payment was made.
     *
     * @param paymentDate the payment date to set
     */
    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * Returns the remaining balance on the loan after the payment.
     *
     * @return the new remaining balance
     */
    public BigDecimal getNewRemainingBalance() {
        return newRemainingBalance;
    }

    /**
     * Sets the remaining balance on the loan after the payment.
     *
     * @param newRemainingBalance the new remaining balance to set
     */
    public void setNewRemainingBalance(BigDecimal newRemainingBalance) {
        this.newRemainingBalance = newRemainingBalance;
    }

    /**
     * Returns the current status of the loan.
     *
     * @return the loan status
     */
    public String getLoanStatus() {
        return loanStatus;
    }

    /**
     * Sets the current status of the loan.
     *
     * @param loanStatus the loan status to set
     */
    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }
}

