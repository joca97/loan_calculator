package com.leanpay.loancalculator.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyInstallmentAmountResponseDTO {

    private BigDecimal amount;

    private Integer numberOfMonth;

}
