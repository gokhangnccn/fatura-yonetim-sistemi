package com.dicore.fatura_yonetim_sistemi.dtos.request;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class NotificationRequestDTO {

    @NotBlank(message = "Message is required")
    private String message;

    @NotNull(message = "User ID is required")
    private Long userId;

    private boolean isRead = false;
}

