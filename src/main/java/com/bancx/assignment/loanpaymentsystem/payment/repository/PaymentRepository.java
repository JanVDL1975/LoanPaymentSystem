package com.bancx.assignment.loanpaymentsystem.payment.repository;

import com.bancx.assignment.loanpaymentsystem.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Payment} entities.
 *
 * Extends {@link JpaRepository} to provide CRUD operations and
 * database access methods for Payment objects.
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {}
