package com.leanpay.loancalculator.util;

import com.leanpay.loancalculator.controller.dto.response.InstallmentResponseDto;
import com.leanpay.loancalculator.domain.Installment;

public class InstallmentMapper {

    public static InstallmentResponseDto map(Installment installment) {
        return InstallmentResponseDto.builder()
                .amount(installment.getAmount())
                .totalInterestAmount(installment.getLoan().getTotalInterestAmount()).build();
    }

}
