package com.leanpay.loancalculator.controller.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.leanpay.loancalculator.util.BigDecimalSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Installment amounts per months response")
public class InstallmentAmountsPerMonthsResponseDto {

    @JsonSerialize(using = BigDecimalSerialize.class)
    @Schema(name = "totalPayments",
            description = "Total payment including loan amount and total interest on the loan.",
            required = true)
    private BigDecimal totalPayments;

    @JsonSerialize(using = BigDecimalSerialize.class)
    @Schema(name = "totalInterest",
            description = "Total interest on the loan.",
            required = true)
    private BigDecimal totalInterest;

    @Schema(name = "installmentAmountsPerMonths",
            description = "List of installments by months.",
            required = true)
    private List<MonthlyInstallmentAmountResponseDto> installmentAmountsPerMonths;

}
