package com.bancx.assignment.loanpaymentsystem.payment.controller;

import com.bancx.assignment.loanpaymentsystem.payment.dto.PaymentRequestDto;
import com.bancx.assignment.loanpaymentsystem.payment.model.Payment;
import com.bancx.assignment.loanpaymentsystem.payment.service.PaymentService;
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

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<Payment> makePayment(@RequestBody PaymentRequestDto dto) {
        return new ResponseEntity<>(paymentService.recordPayment(dto), HttpStatus.CREATED);
    }
}
