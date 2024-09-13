package com.dicore.fatura_yonetim_sistemi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EntityAlreadyExistsException extends RuntimeException {
    private final HttpStatus status;

    public EntityAlreadyExistsException(String entityName, String identifier) {
        super(entityName + " with identifier '" + identifier + "' already exists.");
        this.status = HttpStatus.CONFLICT;
    }

    public EntityAlreadyExistsException(String entityName, Long id) {
        super(entityName + " with ID " + id + " already exists.");
        this.status = HttpStatus.CONFLICT;
    }
}

