package com.leanpay.loancalculator.service.impl;

import com.leanpay.loancalculator.domain.Installment;
import com.leanpay.loancalculator.domain.Loan;
import com.leanpay.loancalculator.repository.InstallmentRepository;
import com.leanpay.loancalculator.repository.LoanRepository;
import com.leanpay.loancalculator.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final InstallmentRepository installmentRepository;

    @Override
    @Transactional
    public Installment calculateMonthlyInstallmentAmount(Loan loan) {
        BigDecimal monthlyPaymentAmount = calculateMonthlyPaymentAmount(loan);
        BigDecimal totalInterestAmount = calculateTotalInterestAmount(loan, monthlyPaymentAmount);

        loan.setNumberOfMonths(loan.getNumberOfMonths());
        loan.setTotalInterestAmount(totalInterestAmount);
        loan = loanRepository.save(loan);

        Installment installment = Installment.builder()
                .amount(monthlyPaymentAmount.setScale(2, RoundingMode.HALF_EVEN))
                .loan(loan).build();
        installment = installmentRepository.save(installment);

        return installment;
    }

    @Override
    @Transactional
    public List<Installment> calculateInstallmentAmountsPerMonths(Loan loan) {
        List<Installment> installments = new ArrayList<>();
        BigDecimal monthlyPaymentAmount = calculateMonthlyPaymentAmount(loan);

        for (int i = 1; i <= loan.getNumberOfMonths(); i++) {
            Installment installment = Installment.builder()
                    .amount(monthlyPaymentAmount.setScale(2, RoundingMode.HALF_EVEN))
                    .numberOfMonth(i)
                    .loan(loan).build();
            installments.add(installment);
        }

        BigDecimal totalInterestAmount = calculateTotalInterestAmount(loan, monthlyPaymentAmount);
        loan.setTotalInterestAmount(totalInterestAmount);
        loanRepository.save(loan);
        installments = installmentRepository.saveAll(installments);

        return installments;
    }

    private BigDecimal calculateMonthlyPaymentAmount(Loan loan) {
        BigDecimal interestRatePerMonth = BigDecimal.valueOf(loan.getInterestRate() / 100 / 12);

        return (loan.getAmount().multiply(interestRatePerMonth).multiply(BigDecimal.ONE.add(interestRatePerMonth).pow(loan.getNumberOfMonths())))
                .divide(BigDecimal.ONE.add(interestRatePerMonth).pow(loan.getNumberOfMonths()).subtract(BigDecimal.ONE), RoundingMode.HALF_EVEN);
    }

    private BigDecimal calculateTotalInterestAmount(Loan loan, BigDecimal monthlyPaymentAmount) {
        return monthlyPaymentAmount.multiply(BigDecimal.valueOf(loan.getNumberOfMonths()))
                .subtract(loan.getAmount())
                .setScale(2, RoundingMode.HALF_EVEN);
    }
}
