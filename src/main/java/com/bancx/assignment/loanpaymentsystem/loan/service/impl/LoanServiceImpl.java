package com.bancx.assignment.loanpaymentsystem.loan.service.impl;

import com.bancx.assignment.loanpaymentsystem.exception.ResourceNotFoundException;
import com.bancx.assignment.loanpaymentsystem.loan.constants.LoanMessageConstants;
import com.bancx.assignment.loanpaymentsystem.loan.dto.LoanRequestDto;
import com.bancx.assignment.loanpaymentsystem.loan.dto.LoanResponseDto;
import com.bancx.assignment.loanpaymentsystem.loan.model.Loan;
import com.bancx.assignment.loanpaymentsystem.loan.model.LoanStatus;
import com.bancx.assignment.loanpaymentsystem.loan.repository.LoanRepository;
import com.bancx.assignment.loanpaymentsystem.loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of the LoanService interface that provides
 * business logic for loan creation and retrieval.
 *
 * This service interacts with the LoanRepository to persist
 * and fetch loan data, converting between entity and DTO objects.
 */
@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    /**
     * Creates a new loan record from the provided LoanRequestDto.
     * Initializes the remaining balance to the loan amount and sets the status to ACTIVE.
     * Saves the loan entity and returns a LoanResponseDto representing the created loan.
     *
     * @param dto Data transfer object containing loan amount and term.
     * @return LoanResponseDto containing details of the newly created loan.
     */
    public LoanResponseDto createLoan(LoanRequestDto dto) {
        Loan loan = new Loan();
        loan.setLoanAmount(dto.getLoanAmount());
        loan.setRemainingBalance(dto.getLoanAmount());
        loan.setTerm(dto.getTerm());
        loan.setStatus(LoanStatus.ACTIVE);

        Loan loanResponse = loanRepository.save(loan);
        LoanResponseDto response = new LoanResponseDto();
        response.setLoanAmount(loanResponse.getLoanAmount());
        response.setTerm(loanResponse.getTerm());
        response.setRemainingBalance(loanResponse.getRemainingBalance());
        return response;
    }

    /**
     * Retrieves the details of a loan by its ID.
     * Throws ResourceNotFoundException if the loan does not exist.
     *
     * @param loanId The ID of the loan to retrieve.
     * @return LoanResponseDto containing the loan's details.
     * @throws ResourceNotFoundException if no loan is found for the given ID.
     */
    public LoanResponseDto getLoanDetails(Long loanId) {
        Loan loanResponse = loanRepository.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException(LoanMessageConstants.LOAN_NOT_FOUND));
        LoanResponseDto response = new LoanResponseDto();
        response.setLoanAmount(loanResponse.getLoanAmount());
        response.setTerm(loanResponse.getTerm());
        response.setRemainingBalance(loanResponse.getRemainingBalance());
        response.setStatus(loanResponse.getStatus());
        return response;
    }
}


