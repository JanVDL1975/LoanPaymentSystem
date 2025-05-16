package com.bancx.assignment.loanpaymentsystem.loan.constants;

/**
 * Utility class that contains constant messages related to loan validations and error handling.
 * <p>
 * This class is final and has a private constructor to prevent instantiation.
 * It is used to centralize and manage error or validation messages for loan operations.
 */
public final class LoanMessageConstants {
    private LoanMessageConstants() {

    }

    /** Message indicating the loan was not found. */
    public static final String LOAN_NOT_FOUND = "Loan not found";

    /** Message indicating the loan amount is required. */
    public static final String LOAN_AMOUNT_REQUIRED = "Loan amount is required";

    /** Message indicating the loan amount must be greater than zero. */
    public static final String LOAN_AMOUNT_GREATER_ZERO = "Loan amount must be greater than zero";

    /** Message indicating the loan term is required. */
    public static final String TERM_REQUIRED = "Term is required";

    /** Message indicating the loan term must be greater than zero. */
    public static final String TERM_GREATER_ZERO = "Term must be greater than zero";
}
