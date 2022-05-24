package com.leanpay.loancalculator.it;

import com.leanpay.loancalculator.AbstractIntegrationTest;
import com.leanpay.loancalculator.controller.dto.request.LoanRequestDto;
import com.leanpay.loancalculator.domain.Installment;
import com.leanpay.loancalculator.domain.Loan;
import com.leanpay.loancalculator.exception.ErrorCode;
import com.leanpay.loancalculator.repository.InstallmentRepository;
import com.leanpay.loancalculator.repository.LoanRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LoanIntegrationTest extends AbstractIntegrationTest {
    private static final String basePath = "/loans/calculator";

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private InstallmentRepository installmentRepository;

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

        List<Loan> loans = loanRepository.findAll();
        List<Installment> installments = installmentRepository.findAll();

        assertEquals(1, loans.size());
        assertEquals(1, installments.size());
    }

    @Test
    void getMonthlyInstallmentAmount_loanAmountHasZeroValue_shouldThrowInvalidRequestParametersException() throws Exception {
        LoanRequestDto loanRequestDto = LoanRequestDto.builder()
                .amount(BigDecimal.ZERO)
                .numberOfMonths(0)
                .interestRate(0d).build();
        String content = objectMapper.writeValueAsString(loanRequestDto);

        mockMvc.perform(post(basePath + "/simple")
                        .locale(Locale.US)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value(ErrorCode.INVALID_REQUEST_PARAMETERS.name()))
                .andExpect(jsonPath("$.error_description")
                        .value(messageSource.getMessage(ErrorCode.INVALID_REQUEST_PARAMETERS.getErrorCode(), null, Locale.US)))
                .andExpect(jsonPath("$.messages").isArray());
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

        List<Loan> loans = loanRepository.findAll();
        List<Installment> installments = installmentRepository.findAll();

        assertEquals(1, loans.size());
        assertEquals(loanRequestDto.getNumberOfMonths(), installments.size());
    }

}
