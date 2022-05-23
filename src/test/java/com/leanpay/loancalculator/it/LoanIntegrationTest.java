package com.leanpay.loancalculator.it;

import com.leanpay.loancalculator.AbstractIntegrationTest;
import com.leanpay.loancalculator.controller.dto.request.LoanRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LoanIntegrationTest extends AbstractIntegrationTest {
    private static final String basePath = "/loans/calculator";

    @Test
    void getMonthlyInstallmentAmount() throws Exception {
        LoanRequestDto loanRequestDto = LoanRequestDto.builder()
                .amount(BigDecimal.valueOf(20000))
                .numberOfMonths(60)
                .interestRate(4.99).build();
        String content = objectMapper.writeValueAsString(loanRequestDto);

        mockMvc.perform(post(basePath + "/simple")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());
    }

    @Test
    void getInstallmentAmountsPerMonths() throws Exception {
        LoanRequestDto loanRequestDto = LoanRequestDto.builder()
                .amount(BigDecimal.valueOf(20000))
                .numberOfMonths(60)
                .interestRate(4.99).build();
        String content = objectMapper.writeValueAsString(loanRequestDto);

        mockMvc.perform(post(basePath + "/amortization-schedule")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk());
    }

}
