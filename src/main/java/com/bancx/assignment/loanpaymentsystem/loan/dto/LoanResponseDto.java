package com.bancx.assignment.loanpaymentsystem.loan.dto;

import com.bancx.assignment.loanpaymentsystem.loan.model.LoanStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class LoanResponseDto {
    private BigDecimal loanAmount;
    private int term;
    private LoanStatus status = LoanStatus.ACTIVE;
    private BigDecimal remainingBalance;

    public LoanResponseDto() {}

    public LoanResponseDto(BigDecimal loanAmount, int term, LoanStatus status, BigDecimal remainingBalance) {
        this.loanAmount = loanAmount;
        this.term = term;
        this.status = status;
        this.remainingBalance = remainingBalance;
    }

    public LoanResponseDto(BigDecimal loanAmount, int term) {
        this.loanAmount = loanAmount;
        this.term = term;
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

    public void setTerm(int term) {
        this.term = term;
    }
}
