package com.leanpay.loancalculator.service.impl;

import com.leanpay.loancalculator.domain.Installment;
import com.leanpay.loancalculator.domain.Loan;
import com.leanpay.loancalculator.repository.LoanRepository;
import com.leanpay.loancalculator.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;

    @Override
    @Transactional
    public Installment getMonthlyInstallmentAmount(Loan loan) {
        BigDecimal monthlyPaymentAmount = calculateMonthlyPaymentAmount(loan);
        BigDecimal totalInterestAmount = calculateTotalInterestAmount(loan, monthlyPaymentAmount);
        Installment installment = Installment.builder()
                .amount(monthlyPaymentAmount).build();

        loan.setNumberOfMonths(loan.getNumberOfMonths());
        loan.setTotalInterestAmount(totalInterestAmount);
        loan.addInstallment(installment);
        loanRepository.save(loan);

        return installment;
    }

    @Override
    @Transactional
    public List<Installment> getInstallmentAmountsPerMonths(Loan loan) {
        BigDecimal monthlyPaymentAmount = calculateMonthlyPaymentAmount(loan);

        for (int i = 1; i <= loan.getNumberOfMonths(); i++) {
            Installment installment = Installment.builder()
                    .amount(monthlyPaymentAmount)
                    .numberOfMonth(i).build();
            loan.addInstallment(installment);
        }

        BigDecimal totalInterestAmount = calculateTotalInterestAmount(loan, monthlyPaymentAmount);
        loan.setTotalInterestAmount(totalInterestAmount);
        loanRepository.save(loan);

        return loan.getInstallments();
    }

    private BigDecimal calculateMonthlyPaymentAmount(Loan loan) {
        BigDecimal interestRatePerMonth = BigDecimal.valueOf(loan.getInterestRate() / 100 / 12);

        return (loan.getAmount().multiply(interestRatePerMonth).multiply((BigDecimal.ONE.add(interestRatePerMonth)).pow(loan.getNumberOfMonths())))
                .divide(((BigDecimal.ONE.add(interestRatePerMonth)).pow(loan.getNumberOfMonths())).subtract(BigDecimal.ONE), RoundingMode.HALF_EVEN);
    }

    private BigDecimal calculateTotalInterestAmount(Loan loan, BigDecimal monthlyPaymentAmount) {
        return monthlyPaymentAmount.multiply(BigDecimal.valueOf(loan.getNumberOfMonths()))
                .subtract(loan.getAmount());
    }
}
