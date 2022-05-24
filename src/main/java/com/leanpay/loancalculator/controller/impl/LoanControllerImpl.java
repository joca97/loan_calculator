package com.leanpay.loancalculator.controller.impl;

import com.leanpay.loancalculator.controller.LoanController;
import com.leanpay.loancalculator.controller.dto.request.LoanRequestDto;
import com.leanpay.loancalculator.controller.dto.response.InstallmentAmountsPerMonthsResponseDto;
import com.leanpay.loancalculator.controller.dto.response.InstallmentResponseDto;
import com.leanpay.loancalculator.controller.dto.response.MonthlyInstallmentAmountResponseDto;
import com.leanpay.loancalculator.domain.Installment;
import com.leanpay.loancalculator.domain.Loan;
import com.leanpay.loancalculator.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoanControllerImpl implements LoanController {
    private final LoanService loanService;
    private final ModelMapper modelMapper;


    @Override
    public ResponseEntity<InstallmentResponseDto> getMonthlyInstallmentAmount(LoanRequestDto loanRequestDTO) {
        Loan loan = modelMapper.map(loanRequestDTO, Loan.class);
        Installment installment = loanService.getMonthlyInstallmentAmount(loan);
        InstallmentResponseDto installmentResponseDto = InstallmentResponseDto.builder()
                .monthlyInstallmentAmount(installment.getAmount())
                .totalInterestAmount(installment.getLoan().getTotalInterestAmount()).build();

        return new ResponseEntity<>(installmentResponseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InstallmentAmountsPerMonthsResponseDto> getInstallmentAmountsPerMonths(LoanRequestDto loanRequestDTO) {
        Loan loan = modelMapper.map(loanRequestDTO, Loan.class);
        List<Installment> installments = loanService.getInstallmentAmountsPerMonths(loan);
        loan = installments.get(0).getLoan();

        List<MonthlyInstallmentAmountResponseDto> monthlyInstallmentAmountResponseDtos = installments.stream()
                .map(installment -> modelMapper.map(installment, MonthlyInstallmentAmountResponseDto.class)).toList();

        InstallmentAmountsPerMonthsResponseDto installmentAmountsPerMonthsResponseDTO = InstallmentAmountsPerMonthsResponseDto.builder()
                .installmentAmountsPerMonths(monthlyInstallmentAmountResponseDtos)
                .totalPayments(loan.getTotalInterestAmount().add(loan.getAmount()))
                .totalInterest(loan.getTotalInterestAmount()).build();

        return new ResponseEntity<>(installmentAmountsPerMonthsResponseDTO, HttpStatus.OK);
    }
}
