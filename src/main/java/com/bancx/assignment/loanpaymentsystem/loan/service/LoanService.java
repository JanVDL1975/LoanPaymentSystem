package com.bancx.assignment.loanpaymentsystem.loan.service;

import com.bancx.assignment.loanpaymentsystem.loan.dto.LoanRequestDto;
import com.bancx.assignment.loanpaymentsystem.loan.dto.LoanResponseDto;

/**
 * Service interface defining business operations related to loans.
 *
 * Provides methods to create new loans and retrieve existing loan details.
 */
public interface LoanService {

    /**
     * Creates a new loan using the provided loan request data.
     *
     * @param dto Data transfer object containing loan amount and term.
     * @return LoanResponseDto containing details of the created loan.
     */
    LoanResponseDto createLoan(LoanRequestDto dto);

    /**
     * Retrieves details of a loan by its unique identifier.
     *
     * @param loanId The ID of the loan to retrieve.
     * @return LoanResponseDto containing the loan details.
     */
    LoanResponseDto getLoanDetails(Long loanId);
}

