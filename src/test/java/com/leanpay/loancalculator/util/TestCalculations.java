package com.leanpay.loancalculator.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestCalculations {

    public static BigDecimal calculateLoanAmount(Integer numberOfMonths, Double interestRate, BigDecimal monthlyInstallmentAmount) {
        BigDecimal interestRatePerMonth = BigDecimal.valueOf(interestRate / 100 / 12);

        return (monthlyInstallmentAmount.multiply((BigDecimal.ONE.add(interestRatePerMonth).pow(numberOfMonths)).subtract(BigDecimal.ONE)))
                .divide(interestRatePerMonth.multiply((BigDecimal.ONE.add(interestRatePerMonth)).pow(numberOfMonths)), RoundingMode.HALF_EVEN);
    }

}
