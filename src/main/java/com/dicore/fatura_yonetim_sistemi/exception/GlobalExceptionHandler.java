package com.dicore.fatura_yonetim_sistemi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle EntityNotFoundException
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiException> handleEntityNotFoundException(EntityNotFoundException ex) {
        ApiException apiException = new ApiException(
                ex.getMessage(),
                ex.getStatus(),
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, ex.getStatus());
    }

    // Handle InvalidPaymentException
    @ExceptionHandler(InvalidPaymentException.class)
    public ResponseEntity<ApiException> handleInvalidPaymentException(InvalidPaymentException ex) {
        ApiException apiException = new ApiException(
                ex.getMessage(),
                ex.getStatus(),
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, ex.getStatus());
    }

    // Handle EntityAlreadyExistsException
    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ApiException> handleEntityAlreadyExistsException(EntityAlreadyExistsException ex) {
        ApiException apiException = new ApiException(
                ex.getMessage(),
                ex.getStatus(),
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiException> handleGeneralException(Exception ex) {
        ApiException apiException = new ApiException(
                "An unexpected error occurred.",
                HttpStatus.INTERNAL_SERVER_ERROR,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
