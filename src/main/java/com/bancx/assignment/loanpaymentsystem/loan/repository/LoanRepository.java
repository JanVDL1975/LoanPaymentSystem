package com.bancx.assignment.loanpaymentsystem.loan.repository;

import com.bancx.assignment.loanpaymentsystem.loan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Loan entities.
 * Extends JpaRepository to provide CRUD operations and
 * database interaction capabilities for Loan objects.
 */
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
}
