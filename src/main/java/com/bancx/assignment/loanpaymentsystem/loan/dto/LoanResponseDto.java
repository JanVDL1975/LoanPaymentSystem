package com.bancx.assignment.loanpaymentsystem.loan.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
public class LoanRequestDto {

    @NotNull(message = "Loan amount is required")
    @Positive(message = "Loan amount must be greater than zero")
    private BigDecimal loanAmount;


    @NotNull(message = "Term is required")
    @Positive(message = "Term must be greater than zero")
    private int term;

    public LoanRequestDto() {}

    public LoanRequestDto(BigDecimal loanAmount, int term) {
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

    public void setTerm(int term) {
        this.term = term;
    }
}
