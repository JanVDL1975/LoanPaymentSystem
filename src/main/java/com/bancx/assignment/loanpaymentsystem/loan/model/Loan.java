package com.bancx.assignment.loanpaymentsystem.loan.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Loan {
    @Id
    @GeneratedValue
    private Long loanId;

    private BigDecimal loanAmount;
    private int term; // in months

    @Enumerated(EnumType.STRING)
    private LoanStatus status = LoanStatus.ACTIVE;

    private BigDecimal remainingBalance;

    public Loan() {

    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public BigDecimal getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(BigDecimal remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    public Loan(Long loanId, BigDecimal loanAmount, int term, LoanStatus status, BigDecimal remainingBalance) {
        this.loanId = loanId;
        this.loanAmount = loanAmount;
        this.term = term;
        this.status = status;
        this.remainingBalance = remainingBalance;
    }
}
