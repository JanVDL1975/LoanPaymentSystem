package com.bancx.assignment.loanpaymentsystem.payment.constants;

/**
 * Utility class that holds constant error messages related to payment validation
 * and loan existence. This class provides reusable message strings for consistent
 * error handling throughout the payment processing logic.
 */
public class PaymentMessageConstants {

    private PaymentMessageConstants() {
        // Private constructor to prevent instantiation
    }

    /** Message indicating that the specified loan was not found. */
    public static final String LOAN_NOT_FOUND = "Loan not found";

    /** Message indicating that the payment amount exceeds the remaining loan balance. */
    public static final String PAYMENT_EXCEEDS_REMAINING = "Payment exceeds the remaining balance";

    /** Message indicating that the payment amount must be a positive value. */
    public static final String PAYMENT_MUST_BE_POSITIVE = "Payment must be positive";
}

