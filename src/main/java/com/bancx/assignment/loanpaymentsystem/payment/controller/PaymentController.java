package com.bancx.assignment.loanpaymentsystem.payment.controller;

import com.bancx.assignment.loanpaymentsystem.payment.dto.PaymentRequestDto;
import com.bancx.assignment.loanpaymentsystem.payment.dto.PaymentResponseDto;
import com.bancx.assignment.loanpaymentsystem.payment.service.PaymentService;
import com.bancx.assignment.loanpaymentsystem.payment.service.impl.PaymentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for handling payment-related operations.
 * <p>
 * Exposes endpoints to record payments against loans. Validates incoming
 * payment requests and returns appropriate responses.
 */
@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * Constructs a PaymentController with the specified PaymentService.
     *
     * @param paymentService the payment service used for processing payments
     */
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Handles HTTP POST requests to record a new payment.
     * <p>
     * Validates the request body and returns the recorded payment details
     * with HTTP status 201 (Created).
     *
     * @param dto the payment request data transfer object containing payment details
     * @return a ResponseEntity containing the payment response DTO and HTTP status
     */
    @PostMapping
    public ResponseEntity<PaymentResponseDto> makePayment(@Valid @RequestBody PaymentRequestDto dto) {
        PaymentResponseDto response = paymentService.recordPayment(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
