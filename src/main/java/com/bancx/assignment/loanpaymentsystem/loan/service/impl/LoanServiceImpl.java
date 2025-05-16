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

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

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

