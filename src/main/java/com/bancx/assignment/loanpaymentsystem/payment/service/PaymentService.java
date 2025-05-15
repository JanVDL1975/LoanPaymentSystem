package com.bancx.assignment.loanpaymentsystem.payment.service;

import com.bancx.assignment.loanpaymentsystem.payment.dto.PaymentRequestDto;
import com.bancx.assignment.loanpaymentsystem.payment.dto.PaymentResponseDto;

public interface PaymentService {
    PaymentResponseDto recordPayment(PaymentRequestDto dto);
}

