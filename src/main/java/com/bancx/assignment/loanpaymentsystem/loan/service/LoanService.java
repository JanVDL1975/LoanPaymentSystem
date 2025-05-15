package com.bancx.assignment.loanpaymentsystem.loan.service;

import com.bancx.assignment.loanpaymentsystem.loan.dto.LoanRequestDto;
import com.bancx.assignment.loanpaymentsystem.loan.dto.LoanResponseDto;

public interface LoanService {
    LoanResponseDto createLoan(LoanRequestDto dto);
    LoanResponseDto getLoanDetails(Long loanId);
}

