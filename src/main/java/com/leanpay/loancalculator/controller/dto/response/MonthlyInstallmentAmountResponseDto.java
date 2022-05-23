package com.leanpay.loancalculator.controller.dto.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.leanpay.loancalculator.util.BigDecimalSerialize;
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
@Schema(name = "Monthly installment amount response")
public class MonthlyInstallmentAmountResponseDto {

    @JsonSerialize(using = BigDecimalSerialize.class)
    @Schema(name = "amount",
            description = "The size or value of the installment.",
            required = true)
    private BigDecimal amount;

    @Schema(name = "numberOfMonth",
            description = "Number of the current month.",
            required = true)
    private Integer numberOfMonth;

}
