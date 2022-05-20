package com.leanpay.loancalculator.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequestDTO {

    private BigDecimal amount;

    private Integer interestRate;

    private Integer numberOfMonths;

}
