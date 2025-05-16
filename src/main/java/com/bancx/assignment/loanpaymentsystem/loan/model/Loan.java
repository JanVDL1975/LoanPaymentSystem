package com.bancx.assignment.loanpaymentsystem.loan.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

/**
 * Represents a loan entity in the database.
 * This class is a JPA entity mapped to a loan table.
 * It contains details such as loan ID, amount, term in months,
 * current status, and the remaining balance.
 */
@Entity
public class Loan {

    /**
     * Unique identifier for the loan, generated automatically.
     */
    @Id
    @GeneratedValue
    private Long loanId;

    /**
     * The total amount of the loan.
     */
    private BigDecimal loanAmount;

    /**
     * The loan term/duration in months.
     */
    private int term;

    /**
     * The current status of the loan, stored as a string enum.
     */
    @Enumerated(EnumType.STRING)
    private LoanStatus status = LoanStatus.ACTIVE;

    /**
     * The remaining balance to be paid on the loan.
     */
    private BigDecimal remainingBalance;

    public Loan() {
        // Default constructor required by JPA
    }

    /**
     * Constructs a Loan with all properties.
     *
     * @param loanId           the unique loan ID
     * @param loanAmount       the loan amount
     * @param term             the loan term in months
     * @param status           the current status of the loan
     * @param remainingBalance the remaining balance on the loan
     */
    public Loan(Long loanId, BigDecimal loanAmount, int term, LoanStatus status, BigDecimal remainingBalance) {
        this.loanId = loanId;
        this.loanAmount = loanAmount;
        this.term = term;
        this.status = status;
        this.remainingBalance = remainingBalance;
    }

    // Getters and setters with brief descriptions

    /**
     * Returns the unique loan ID.
     * @return loan ID
     */
    public Long getLoanId() {
        return loanId;
    }

    /**
     * Sets the unique loan ID.
     * @param loanId the loan ID to set
     */
    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    /**
     * Returns the loan amount.
     * @return loan amount
     */
    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    /**
     * Sets the loan amount.
     * @param loanAmount the amount to set
     */
    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    /**
     * Returns the loan term in months.
     * @return term in months
     */
    public int getTerm() {
        return term;
    }

    /**
     * Sets the loan term in months.
     * @param term the term to set
     */
    public void setTerm(int term) {
        this.term = term;
    }

    /**
     * Returns the current loan status.
     * @return loan status
     */
    public LoanStatus getStatus() {
        return status;
    }

    /**
     * Sets the current loan status.
     * @param status the status to set
     */
    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    /**
     * Returns the remaining balance of the loan.
     * @return remaining balance
     */
    public BigDecimal getRemainingBalance() {
        return remainingBalance;
    }

    /**
     * Sets the remaining balance of the loan.
     * @param remainingBalance the balance to set
     */
    public void setRemainingBalance(BigDecimal remainingBalance) {
        this.remainingBalance = remainingBalance;
    }
}

