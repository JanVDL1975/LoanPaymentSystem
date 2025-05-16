package com.bancx.assignment.loanpaymentsystem.loan.dto;

import com.bancx.assignment.loanpaymentsystem.loan.model.LoanStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

/**
 * LoanResponseDto is a Data Transfer Object used to convey loan details
 * in API responses.
 * <p>
 * It includes the loan amount, loan term (in months), current loan status,
 * and the remaining balance on the loan.
 */
public class LoanResponseDto {
    /**
     * The total amount of the loan.
     */
    private BigDecimal loanAmount;

    /**
     * The term or duration of the loan in months.
     */
    private int term;

    /**
     * The current status of the loan.
     * Defaults to ACTIVE.
     */
    private LoanStatus status = LoanStatus.ACTIVE;

    /**
     * The remaining balance to be paid on the loan.
     */
    private BigDecimal remainingBalance;

    /**
     * Default no-args constructor.
     */
    public LoanResponseDto() {}

    /**
     * Constructs a LoanResponseDto with all fields.
     *
     * @param loanAmount       the loan amount
     * @param term             the loan term in months
     * @param status           the current loan status
     * @param remainingBalance the remaining balance
     */
    public LoanResponseDto(BigDecimal loanAmount, int term, LoanStatus status, BigDecimal remainingBalance) {
        this.loanAmount = loanAmount;
        this.term = term;
        this.status = status;
        this.remainingBalance = remainingBalance;
    }

    /**
     * Constructs a LoanResponseDto with loan amount and term only.
     *
     * @param loanAmount the loan amount
     * @param term       the loan term in months
     */
    public LoanResponseDto(BigDecimal loanAmount, int term) {
        this.loanAmount = loanAmount;
        this.term = term;
    }

    /**
     * Gets the total amount of the loan.
     * @return the loan amount
     */
    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    /**
     * Sets the total amount of the loan.
     * @param loanAmount the loan amount to set
     */
    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    /**
     * Gets the term (duration) of the loan in months.
     * @return the loan term
     */
    public int getTerm() {
        return term;
    }

    /**
     * Sets the term (duration) of the loan in months.
     * @param term the loan term to set
     */
    public void setTerm(int term) {
        this.term = term;
    }

    /**
     * Gets the current status of the loan.
     * @return the loan status
     */
    public LoanStatus getStatus() {
        return status;
    }

    /**
     * Sets the current status of the loan.
     * @param status the loan status to set
     */
    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    /**
     * Gets the remaining balance to be paid on the loan.
     * @return the remaining balance
     */
    public BigDecimal getRemainingBalance() {
        return remainingBalance;
    }

    /**
     * Sets the remaining balance to be paid on the loan.
     * @param remainingBalance the remaining balance to set
     */
    public void setRemainingBalance(BigDecimal remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

}

