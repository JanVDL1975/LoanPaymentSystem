package com.bancx.assignment.loanpaymentsystem.loan.service;

import com.bancx.assignment.loanpaymentsystem.exception.ResourceNotFoundException;
import com.bancx.assignment.loanpaymentsystem.loan.dto.LoanRequestDto;
import com.bancx.assignment.loanpaymentsystem.loan.model.Loan;
import com.bancx.assignment.loanpaymentsystem.loan.model.LoanStatus;
import com.bancx.assignment.loanpaymentsystem.loan.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public Loan createLoan(LoanRequestDto dto) {
        Loan loan = new Loan();
        loan.setLoanAmount(dto.getLoanAmount());
        loan.setRemainingBalance(dto.getLoanAmount());
        loan.setTerm(dto.getTerm());
        loan.setStatus(LoanStatus.ACTIVE);
        return loanRepository.save(loan);
    }

    public Loan getLoanDetails(Long loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found"));
    }
}

