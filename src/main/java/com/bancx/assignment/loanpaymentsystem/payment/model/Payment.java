package com.bancx.assignment.loanpaymentsystem.payment.model;

import com.bancx.assignment.loanpaymentsystem.loan.model.Loan;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Payment {
    @Id
    @GeneratedValue
    private Long paymentId;

    @ManyToOne
    private Loan loan;

    private BigDecimal paymentAmount;

    private LocalDate paymentDate = LocalDate.now();

    public Payment() {

    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

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

    public Payment(Long paymentId, Loan loan, BigDecimal paymentAmount, LocalDate paymentDate) {
        this.paymentId = paymentId;
        this.loan = loan;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }
}

