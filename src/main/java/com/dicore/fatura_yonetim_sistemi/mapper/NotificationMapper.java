package com.dicore.fatura_yonetim_sistemi.mapper;

import com.dicore.fatura_yonetim_sistemi.dtos.request.NotificationRequestDTO;
import com.dicore.fatura_yonetim_sistemi.dtos.response.NotificationResponseDTO;
import com.dicore.fatura_yonetim_sistemi.entity.Notification;
import com.dicore.fatura_yonetim_sistemi.entity.User;

public class NotificationMapper {

    public static Notification toEntity(NotificationRequestDTO dto, User user) {
        Notification notification = new Notification();
        notification.setMessage(dto.getMessage());
        notification.setUser(user);
        notification.setRead(dto.isRead());
        return notification;
    }

    public static NotificationResponseDTO toResponseDTO(Notification notification) {
        NotificationResponseDTO dto = new NotificationResponseDTO();
        dto.setId(notification.getId());
        dto.setMessage(notification.getMessage());
        dto.setUserId(notification.getUser().getId());
        dto.setRead(notification.isRead());
        return dto;
    }
}

