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

/**
 * LoanController handles RESTful endpoints related to loan operations.
 * <p>
 * This controller provides APIs to create a new loan and retrieve details of an existing loan by its ID.
 */
@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    /**
     * Constructor injection for LoanService dependency.
     *
     * @param loanService the service layer handling loan business logic
     */
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    /**
     * Creates a new loan based on the provided loan request data.
     *
     * @param dto the loan request DTO containing loan amount and term
     * @return ResponseEntity containing the created loan details and HTTP status 201 (Created)
     */
    @PostMapping
    public ResponseEntity<LoanResponseDto> createLoan(@Valid @RequestBody LoanRequestDto dto) {
        return new ResponseEntity<>(loanService.createLoan(dto), HttpStatus.CREATED);
    }

    /**
     * Retrieves loan details by loan ID.
     *
     * @param loanId the ID of the loan to be retrieved
     * @return ResponseEntity containing the loan details and HTTP status 200 (OK)
     */
    @GetMapping("/{loanId}")
    public ResponseEntity<LoanResponseDto> getLoan(@Valid @PathVariable Long loanId) {
        return new ResponseEntity<>(loanService.getLoanDetails(loanId), HttpStatus.OK);
    }
}


