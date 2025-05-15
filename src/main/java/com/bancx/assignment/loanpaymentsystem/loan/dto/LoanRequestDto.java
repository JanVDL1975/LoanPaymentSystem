package com.bancx.assignment.loanpaymentsystem.loan.dto;

import com.bancx.assignment.loanpaymentsystem.loan.constants.LoanMessageConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
public class LoanRequestDto {

    @NotNull(message = LoanMessageConstants.LOAN_AMOUNT_REQUIRED)
    @Positive(message = LoanMessageConstants.LOAN_AMOUNT_GREATER_ZERO)
    private BigDecimal loanAmount;


    @NotNull(message = LoanMessageConstants.TERM_REQUIRED)
    @Positive(message = LoanMessageConstants.TERM_GREATER_ZERO)
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
