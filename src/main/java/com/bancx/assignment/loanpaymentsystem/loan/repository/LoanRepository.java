package com.bancx.assignment.loanpaymentsystem.loan.repository;

import com.bancx.assignment.loanpaymentsystem.loan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
