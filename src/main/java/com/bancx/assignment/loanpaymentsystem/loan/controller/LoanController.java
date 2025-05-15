package com.bancx.assignment.loanpaymentsystem.loan.controller;

import com.bancx.assignment.loanpaymentsystem.loan.dto.LoanRequestDto;
import com.bancx.assignment.loanpaymentsystem.loan.dto.LoanResponseDto;
import com.bancx.assignment.loanpaymentsystem.loan.service.LoanService;
import com.bancx.assignment.loanpaymentsystem.loan.service.impl.LoanServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<LoanResponseDto> createLoan(@Valid @RequestBody LoanRequestDto dto) {
        return new ResponseEntity<>(loanService.createLoan(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<LoanResponseDto> getLoan(@Valid @PathVariable Long loanId) {
        return new ResponseEntity<>(loanService.getLoanDetails(loanId), HttpStatus.OK);
    }
}


