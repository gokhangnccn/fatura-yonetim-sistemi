package com.dicore.fatura_yonetim_sistemi.exception;

import org.springframework.http.HttpStatus;
import lombok.Getter;

@Getter
public class InvalidPaymentException extends RuntimeException {
    private final HttpStatus status;

    public InvalidPaymentException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }
}
