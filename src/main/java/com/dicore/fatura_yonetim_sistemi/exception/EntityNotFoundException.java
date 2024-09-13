package com.dicore.fatura_yonetim_sistemi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EntityNotFoundException extends RuntimeException {
    private final HttpStatus status;

    public EntityNotFoundException(String entityName, Long entityId) {
        super(entityName + " with ID " + entityId + " not found.");
        this.status = HttpStatus.NOT_FOUND;
    }

    public EntityNotFoundException(String entityName, String identifier) {
        super(entityName + " with identifier " + identifier + " not found.");
        this.status = HttpStatus.NOT_FOUND;
    }
}
