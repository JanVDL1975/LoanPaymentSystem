package com.bancx.assignment.loanpaymentsystem.loan.dto;

import com.bancx.assignment.loanpaymentsystem.loan.constants.LoanMessageConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * LoanRequestDto is a Data Transfer Object that encapsulates the input data
 * required for creating a new loan.
 * <p>
 * It includes fields for the loan amount and the loan term (in months),
 * and enforces validation constraints to ensure that valid data is provided.
 */
public class LoanRequestDto {

    /**
     * The amount of the loan being requested.
     * Must not be null and must be greater than zero.
     */
    @NotNull(message = LoanMessageConstants.LOAN_AMOUNT_REQUIRED)
    @Positive(message = LoanMessageConstants.LOAN_AMOUNT_GREATER_ZERO)
    private BigDecimal loanAmount;


    /**
     * The term or duration of the loan in months.
     * Must not be null and must be greater than zero.
     */
    @NotNull(message = LoanMessageConstants.TERM_REQUIRED)
    @Positive(message = LoanMessageConstants.TERM_GREATER_ZERO)
    private int term;

    /**
     * Default no-args constructor.
     */
    public LoanRequestDto() {}

    /**
     * Constructs a LoanRequestDto with specified loan amount and term.
     *
     * @param loanAmount the amount of the loan
     * @param term       the duration of the loan in months
     */
    public LoanRequestDto(BigDecimal loanAmount, int term) {
        this.loanAmount = loanAmount;
        this.term = term;
    }

    /**
     * Returns the loan amount.
     *
     * @return the loan amount as a BigDecimal
     */
    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    /**
     * Sets the loan amount.
     *
     * @param loanAmount the amount of the loan to set
     */
    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    /**
     * Returns the term (duration) of the loan.
     *
     * @return the loan term as an integer
     */
    public int getTerm() {
        return term;
    }

    /**
     * Sets the term (duration) of the loan.
     *
     * @param term the loan term to set
     */
    public void setTerm(int term) {
        this.term = term;
    }
}
