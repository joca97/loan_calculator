package com.leanpay.loancalculator.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Loan request")
public class LoanRequestDto {

    @DecimalMin(value = "0.1", message = "value of the amount must be greater than 0.0")
    @NotNull(message = "amount must not be null")
    @Schema(name = "amount",
            description = "The size or value of the loan.",
            required = true,
            minimum = "0.1")
    private BigDecimal amount;

    @DecimalMin(value = "0.1", message = "value of the interest rate must be greater than 0.0")
    @NotNull(message = "interest rate must not be null")
    @Schema(name = "interestRate",
            description = "The annual stated rate of the loan.",
            required = true,
            minimum = "0.1")
    private Double interestRate;

    @Min(value = 1, message = "value of the number of the months must be greater than 0")
    @NotNull(message = "number of months must not be null")
    @Schema(name = "numberOfMonths",
            description = "The total number of payments, initial or remaining, to pay off the given loan amount.",
            required = true,
            minimum = "1")
    private Integer numberOfMonths;

}
