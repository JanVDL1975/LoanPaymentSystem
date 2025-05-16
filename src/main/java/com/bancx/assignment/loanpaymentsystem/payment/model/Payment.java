package com.bancx.assignment.loanpaymentsystem.payment.model;

import com.bancx.assignment.loanpaymentsystem.loan.model.Loan;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Payment is a JPA entity representing a payment made towards a loan.
 * It stores details such as the payment ID, associated loan, payment amount,
 * and the date the payment was made.
 */
@Entity
public class Payment {
    /** Unique identifier for the payment. */
    @Id
    @GeneratedValue
    private Long paymentId;

    /** The loan to which this payment belongs. */
    @ManyToOne
    private Loan loan;

    /** The amount paid in this payment. */
    private BigDecimal paymentAmount;

    /** The date when the payment was made. Defaults to current date. */
    private LocalDate paymentDate = LocalDate.now();

    /**
     * Default constructor required by JPA.
     */
    public Payment() {

    }

    /**
     * Gets the payment ID.
     *
     * @return the payment ID
     */
    public Long getPaymentId() {
        return paymentId;
    }

    /**
     * Sets the payment ID.
     *
     * @param paymentId the payment ID to set
     */
    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * Gets the associated loan.
     *
     * @return the loan
     */
    public Loan getLoan() {
        return loan;
    }

    /**
     * Sets the associated loan.
     *
     * @param loan the loan to set
     */
    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    /**
     * Gets the payment amount.
     *
     * @return the payment amount
     */
    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Sets the payment amount.
     *
     * @param paymentAmount the payment amount to set
     */
    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    /**
     * Gets the payment date.
     *
     * @return the payment date
     */
    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    /**
     * Sets the payment date.
     *
     * @param paymentDate the payment date to set
     */
    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * Constructs a Payment with all fields.
     *
     * @param paymentId     the payment ID
     * @param loan          the associated loan
     * @param paymentAmount the payment amount
     * @param paymentDate   the date of payment
     */
    public Payment(Long paymentId, Loan loan, BigDecimal paymentAmount, LocalDate paymentDate) {
        this.paymentId = paymentId;
        this.loan = loan;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }
}

