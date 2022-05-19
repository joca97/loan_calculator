package com.leanpay.loancalculator.controller.impl;

import com.leanpay.loancalculator.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoanControllerImpl {
    private final LoanService loanService;


}
