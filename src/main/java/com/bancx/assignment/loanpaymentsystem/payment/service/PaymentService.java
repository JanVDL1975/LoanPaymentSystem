package com.bancx.assignment.loanpaymentsystem.payment.service;

import com.bancx.assignment.loanpaymentsystem.payment.dto.PaymentRequestDto;
import com.bancx.assignment.loanpaymentsystem.payment.dto.PaymentResponseDto;

/**
 * Service interface defining operations related to payments.
 */
public interface PaymentService {

    /**
     * Records a payment made towards a loan.
     *
     * @param dto the payment request data transfer object containing payment details
     * @return a payment response DTO containing details of the recorded payment
     */
    PaymentResponseDto recordPayment(PaymentRequestDto dto);
}

