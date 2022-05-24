package com.leanpay.loancalculator.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ErrorHandler extends ResponseEntityExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleExceptions(Exception ex, Locale locale) {
        String errorMessage = messageSource.getMessage(ErrorCode.UNEXPECTED_ERROR.getErrorCode(), null, locale);

        log.error(errorMessage, ex);

        ErrorMessage restMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, ErrorCode.UNEXPECTED_ERROR);
        return buildResponseEntity(restMessage);
    }

    protected @NotNull ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                           HttpHeaders headers, HttpStatus status, WebRequest request) {
        Locale locale = request.getLocale();
        String errorMessage = messageSource.getMessage(ErrorCode.INVALID_REQUEST_PARAMETERS.getErrorCode(), null, locale);

        log.error(errorMessage, ex);

        ErrorMessage restMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, errorMessage, ErrorCode.INVALID_REQUEST_PARAMETERS);
        BindingResult result = ex.getBindingResult();
        List<String> errorMessages = result.getAllErrors()
                .stream()
                .map(objectError -> messageSource.getMessage(objectError, locale))
                .collect(Collectors.toList());
        restMessage.setMessages(errorMessages);
        return buildResponseEntity(restMessage);
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorMessage restMessage) {
        return new ResponseEntity<>(restMessage, restMessage.getStatus());
    }

}
