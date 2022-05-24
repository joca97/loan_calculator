package com.leanpay.loancalculator.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

@Data
public class ErrorMessage {

    @JsonIgnore
    private HttpStatus status;
    private Instant timestamp;
    private String error_description;
    private ErrorCode error;
    private List<String> messages;

    public ErrorMessage() {
        this.timestamp = Instant.now();
    }

    public ErrorMessage(HttpStatus status, String error_description, ErrorCode error) {
        this();
        this.status = status;
        this.error_description = error_description;
        this.error = error;
    }

}
