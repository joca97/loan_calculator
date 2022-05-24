package com.leanpay.loancalculator.controller;

import com.leanpay.loancalculator.controller.dto.request.LoanRequestDto;
import com.leanpay.loancalculator.controller.dto.response.InstallmentAmountsPerMonthsResponseDto;
import com.leanpay.loancalculator.controller.dto.response.InstallmentResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Loan Controller",
        description = "Calculate monthly payment on a loan from a term" +
                " in years or months along with interest paid on the loan.")
@RequestMapping(value = "loans/calculator", produces = APPLICATION_JSON_VALUE)
public interface LoanController {

    @Operation(summary = "Simple Loan Calculator",
            description = "Use this loan calculator for a simple calculation of your monthly payment along with interest paid on the loan.",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = InstallmentResponseDto.class)))
            })
    @PostMapping("/simple")
    ResponseEntity<InstallmentResponseDto> getMonthlyInstallmentAmount(@RequestBody @Valid LoanRequestDto loanRequestDTO);

    @Operation(summary = "Amortization Schedule Calculator",
            description = "This amortization schedule calculator allows you to create a payment table" +
                    " for a loan with equal loan payments for the life of a loan. The amortization table " +
                    "shows how each payment is applied to the principal balance and the interest owed.",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = InstallmentAmountsPerMonthsResponseDto.class)))
            })
    @PostMapping("/amortization-schedule")
    ResponseEntity<InstallmentAmountsPerMonthsResponseDto> getInstallmentAmountsPerMonths(@RequestBody @Valid LoanRequestDto loanRequestDTO);

}
