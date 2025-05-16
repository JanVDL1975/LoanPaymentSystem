package com.bancx.assignment.loanpaymentsystem.exception;

/**
 * Custom exception used to indicate that a requested resource was not found.
 * This exception extends {@link RuntimeException} and is typically used in service
 * or controller layers to signal missing data such as a loan or payment.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
