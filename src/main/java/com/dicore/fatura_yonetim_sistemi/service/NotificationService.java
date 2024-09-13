package com.dicore.fatura_yonetim_sistemi.service;

import com.dicore.fatura_yonetim_sistemi.exception.EntityNotFoundException;
import com.dicore.fatura_yonetim_sistemi.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dicore.fatura_yonetim_sistemi.dtos.request.NotificationRequestDTO;
import com.dicore.fatura_yonetim_sistemi.dtos.response.NotificationResponseDTO;
import com.dicore.fatura_yonetim_sistemi.entity.Notification;
import com.dicore.fatura_yonetim_sistemi.entity.User;
import com.dicore.fatura_yonetim_sistemi.mapper.NotificationMapper;
import com.dicore.fatura_yonetim_sistemi.repository.UserRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public NotificationResponseDTO createNotification(NotificationRequestDTO notificationRequestDTO) {
        User user = userRepository.findById(notificationRequestDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User", notificationRequestDTO.getUserId()));

        Notification notification = NotificationMapper.toEntity(notificationRequestDTO, user);
        Notification savedNotification = notificationRepository.save(notification);
        return NotificationMapper.toResponseDTO(savedNotification);
    }

    public NotificationResponseDTO getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notification", id));
        return NotificationMapper.toResponseDTO(notification);
    }

    public List<NotificationResponseDTO> getNotificationsByUserId(Long userId) {
        List<Notification> notifications = notificationRepository.findByUserId(userId);
        return notifications.stream()
                .map(NotificationMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<NotificationResponseDTO> getUnreadNotificationsByUserId(Long userId) {
        List<Notification> notifications = notificationRepository.findUnreadNotificationsByUserId(userId);
        return notifications.stream()
                .map(NotificationMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new EntityNotFoundException("Notification", notificationId));
        notification.setRead(true);
        notificationRepository.save(notification);
    }
}

