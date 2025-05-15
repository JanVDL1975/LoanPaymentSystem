package com.bancx.assignment.loanpaymentsystem.payment.dto;

import com.bancx.assignment.loanpaymentsystem.payment.constants.PaymentMessageConstants;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class PaymentRequestDto {
    @NotNull
    private Long loanId;

    @NotNull
    @DecimalMin(value = "0.01", message = PaymentMessageConstants.PAYMENT_MUST_BE_POSITIVE)
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
