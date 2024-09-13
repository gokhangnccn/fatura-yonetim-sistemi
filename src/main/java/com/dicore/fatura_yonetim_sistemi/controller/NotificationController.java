package com.dicore.fatura_yonetim_sistemi.controller;

import com.dicore.fatura_yonetim_sistemi.dtos.request.NotificationRequestDTO;
import com.dicore.fatura_yonetim_sistemi.dtos.response.NotificationResponseDTO;
import com.dicore.fatura_yonetim_sistemi.service.NotificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@Tag(name = "Bildirim Yönetimi")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<NotificationResponseDTO> createNotification(@Valid @RequestBody NotificationRequestDTO notificationRequestDTO) {
        NotificationResponseDTO createdNotification = notificationService.createNotification(notificationRequestDTO);
        return ResponseEntity.ok(createdNotification);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponseDTO> getNotificationById(@PathVariable Long id) {
        NotificationResponseDTO notification = notificationService.getNotificationById(id);
        return ResponseEntity.ok(notification);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationResponseDTO>> getNotificationsByUserId(@PathVariable Long userId) {
        List<NotificationResponseDTO> notifications = notificationService.getNotificationsByUserId(userId);
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<List<NotificationResponseDTO>> getUnreadNotificationsByUserId(@PathVariable Long userId) {
        List<NotificationResponseDTO> unreadNotifications = notificationService.getUnreadNotificationsByUserId(userId);
        return ResponseEntity.ok(unreadNotifications);
    }

    @PutMapping("/{id}/mark-as-read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
        return ResponseEntity.noContent().build();
    }

}

