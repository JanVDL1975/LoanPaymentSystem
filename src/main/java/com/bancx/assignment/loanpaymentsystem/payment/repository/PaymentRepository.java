package com.bancx.assignment.loanpaymentsystem.payment.repository;

import com.bancx.assignment.loanpaymentsystem.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {}
