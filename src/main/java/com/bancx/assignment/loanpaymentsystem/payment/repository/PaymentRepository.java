package com.bancx.assignment.loanpaymentsystem.payment.repository;

import com.bancx.assignment.loanpaymentsystem.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {}
