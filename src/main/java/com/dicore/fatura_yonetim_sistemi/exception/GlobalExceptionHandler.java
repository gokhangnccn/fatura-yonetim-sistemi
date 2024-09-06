package com.dicore.fatura_yonetim_sistemi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    HttpStatus badRequest = HttpStatus.BAD_REQUEST;


    //validation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiException> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        ApiException apiException = new ApiException(
                errorMessage,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, badRequest);
    }

    //custom
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiException> handleCustomException(CustomException ex) {
        ApiException apiException = new ApiException(
                ex.getMessage(),
                ex.getStatus(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, ex.getStatus());
    }

}
