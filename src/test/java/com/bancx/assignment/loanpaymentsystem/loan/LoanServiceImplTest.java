package com.bancx.assignment.loanpaymentsystem.loan;

import com.bancx.assignment.loanpaymentsystem.loan.dto.LoanResponseDto;
import com.bancx.assignment.loanpaymentsystem.loan.model.Loan;
import com.bancx.assignment.loanpaymentsystem.loan.model.LoanStatus;
import com.bancx.assignment.loanpaymentsystem.loan.repository.LoanRepository;
import com.bancx.assignment.loanpaymentsystem.loan.service.impl.LoanServiceImpl;
import com.bancx.assignment.loanpaymentsystem.loan.dto.LoanRequestDto;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
public class LoanServiceImplTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanServiceImpl loanServiceImpl;

    @Test
    void testLoanCreationSuccessfully() {
        LoanRequestDto dto = new LoanRequestDto();
        dto.setLoanAmount(new BigDecimal("1000.00"));
        dto.setTerm(12);

        Loan loan = new Loan();
        loan.setLoanAmount(dto.getLoanAmount());
        loan.setTerm(dto.getTerm());
        loan.setRemainingBalance(dto.getLoanAmount());
        loan.setStatus(LoanStatus.ACTIVE);

        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        LoanResponseDto saved = loanServiceImpl.createLoan(dto);

        assertNotNull(saved);
        assertEquals(new BigDecimal("1000.00"), saved.getRemainingBalance());
        assertEquals(LoanStatus.ACTIVE, saved.getStatus());
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    void testLoanSettlement() {
        Loan loan = new Loan();
        loan.setLoanId(1L);
        loan.setLoanAmount(new BigDecimal("500.00"));
        loan.setRemainingBalance(BigDecimal.ZERO);
        loan.setTerm(12);
        loan.setStatus(LoanStatus.SETTLED);

        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));

        LoanResponseDto retrieved = loanServiceImpl.getLoanDetails(1L);

        assertEquals(LoanStatus.SETTLED, retrieved.getStatus());
        assertEquals(BigDecimal.ZERO, retrieved.getRemainingBalance());
        assertEquals(new BigDecimal("500.00"), retrieved.getLoanAmount());
    }
}
