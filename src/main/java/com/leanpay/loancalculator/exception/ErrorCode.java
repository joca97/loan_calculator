package com.leanpay.loancalculator.exception;

public enum ErrorCode {
    UNEXPECTED_ERROR("exception.unexpectedError"),
    INVALID_REQUEST_PARAMETERS("exception.invalidRequestParameters"),
    BAD_REQUEST("exception.badRequest");

    private final String errorCode;

    ErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
