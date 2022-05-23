package com.leanpay.loancalculator.controller.dto.response;

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
public class InstallmentAmountsPerMonthsResponseDTO {

    @Schema(name = "totalPayments",
            description = "Total payment including loan amount and total interest on the loan.",
            required = true)
    private BigDecimal totalPayments;

    @Schema(name = "totalInterest",
            description = "Total interest on the loan.",
            required = true)
    private BigDecimal totalInterest;

    @Schema(name = "installmentAmountsPerMonths",
            description = "List of installments by months.",
            required = true)
    private List<MonthlyInstallmentAmountResponseDTO> installmentAmountsPerMonths;

}
