package com.dicore.fatura_yonetim_sistemi.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationResponseDTO {

    private Long id;

    private String message;

    private Long userId;

    private boolean isRead;
}

