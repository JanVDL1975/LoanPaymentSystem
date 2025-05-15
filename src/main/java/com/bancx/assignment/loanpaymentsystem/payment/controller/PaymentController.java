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

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDto> makePayment(@Valid @RequestBody PaymentRequestDto dto) {
        PaymentResponseDto response = paymentService.recordPayment(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
