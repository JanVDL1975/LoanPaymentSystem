package com.bancx.assignment.loanpaymentsystem.payment.dto;

import java.math.BigDecimal;

public class PaymentRequestDto {

    private Long loanId;
    private BigDecimal paymentAmount;

    public PaymentRequestDto() {}

    public PaymentRequestDto(Long loanId, BigDecimal paymentAmount) {
        this.loanId = loanId;
        this.paymentAmount = paymentAmount;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
