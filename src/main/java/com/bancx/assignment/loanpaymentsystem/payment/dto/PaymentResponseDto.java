package com.bancx.assignment.loanpaymentsystem.payment.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentResponseDto {
    private BigDecimal paymentAmount;
    private LocalDate paymentDate;
    private BigDecimal newRemainingBalance;
    private String loanStatus;

    public PaymentResponseDto() {}

    public PaymentResponseDto(BigDecimal paymentAmount, LocalDate paymentDate,
                              BigDecimal newRemainingBalance, String loanStatus) {
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.newRemainingBalance = newRemainingBalance;
        this.loanStatus = loanStatus;
    }

    // Getters and setters
    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getNewRemainingBalance() {
        return newRemainingBalance;
    }

    public void setNewRemainingBalance(BigDecimal newRemainingBalance) {
        this.newRemainingBalance = newRemainingBalance;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }
}

