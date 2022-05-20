package com.leanpay.loancalculator.service;

import com.leanpay.loancalculator.domain.Installment;
import com.leanpay.loancalculator.domain.Loan;

import java.util.List;

public interface LoanService {

    Installment calculateMonthlyInstallmentAmount(Loan loan);

    List<Installment> calculateInstallmentAmountsPerMonths(Loan loan);

}
