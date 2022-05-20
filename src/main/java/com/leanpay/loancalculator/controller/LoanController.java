package com.leanpay.loancalculator.controller;

import com.leanpay.loancalculator.controller.dto.request.LoanRequestDTO;
import com.leanpay.loancalculator.controller.dto.response.InstallmentAmountsPerMonthsResponseDTO;
import com.leanpay.loancalculator.controller.dto.response.InstallmentResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(value = "loans", produces = APPLICATION_JSON_VALUE)
public interface LoanController {

    @PostMapping("/monthly")
    ResponseEntity<InstallmentResponseDto> calculateMonthlyInstallmentAmount(@Valid @RequestBody LoanRequestDTO loanRequestDTO);

    @PostMapping("/perMonths")
    ResponseEntity<InstallmentAmountsPerMonthsResponseDTO> calculateInstallmentAmountsPerMonths(@Valid @RequestBody LoanRequestDTO loanRequestDTO);

}
