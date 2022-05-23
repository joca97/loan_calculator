package com.leanpay.loancalculator.controller.impl;

import com.leanpay.loancalculator.controller.LoanController;
import com.leanpay.loancalculator.controller.dto.request.LoanRequestDto;
import com.leanpay.loancalculator.controller.dto.response.InstallmentAmountsPerMonthsResponseDTO;
import com.leanpay.loancalculator.controller.dto.response.InstallmentResponseDto;
import com.leanpay.loancalculator.controller.dto.response.MonthlyInstallmentAmountResponseDTO;
import com.leanpay.loancalculator.domain.Installment;
import com.leanpay.loancalculator.domain.Loan;
import com.leanpay.loancalculator.service.LoanService;
import com.leanpay.loancalculator.util.InstallmentMapper;
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
    public ResponseEntity<InstallmentResponseDto> calculateMonthlyInstallmentAmount(LoanRequestDto loanRequestDTO) {
        Loan loan = modelMapper.map(loanRequestDTO, Loan.class);
        Installment installment = loanService.calculateMonthlyInstallmentAmount(loan);
        InstallmentResponseDto installmentResponseDto = InstallmentMapper.map(installment);

        return new ResponseEntity<>(installmentResponseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<InstallmentAmountsPerMonthsResponseDTO> calculateInstallmentAmountsPerMonths(LoanRequestDto loanRequestDTO) {
        Loan loan = modelMapper.map(loanRequestDTO, Loan.class);
        List<Installment> installments = loanService.calculateInstallmentAmountsPerMonths(loan);
        List<MonthlyInstallmentAmountResponseDTO> monthlyInstallmentAmountResponseDTOS = installments.stream()
                .map(installment -> modelMapper.map(installment, MonthlyInstallmentAmountResponseDTO.class)).toList();

        InstallmentAmountsPerMonthsResponseDTO installmentAmountsPerMonthsResponseDTO = InstallmentAmountsPerMonthsResponseDTO.builder()
                .installmentAmountsPerMonths(monthlyInstallmentAmountResponseDTOS)
                .totalPayments(installments.get(0).getLoan().getTotalInterestAmount().add(installments.get(0).getLoan().getAmount()))
                .totalInterest(installments.get(0).getLoan().getTotalInterestAmount()).build();

        return new ResponseEntity<>(installmentAmountsPerMonthsResponseDTO, HttpStatus.OK);
    }
}
