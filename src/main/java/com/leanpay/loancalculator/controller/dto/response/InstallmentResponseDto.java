package com.leanpay.loancalculator.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Installment response")
public class InstallmentResponseDto {

    @Schema(name = "monthlyInstallmentAmount",
            description = "The size or value of the installment.",
            required = true)
    private BigDecimal monthlyInstallmentAmount;

    @Schema(name = "totalInterestAmount",
            description = "The size or value of the total interest amount.",
            required = true)
    private BigDecimal totalInterestAmount;

}
