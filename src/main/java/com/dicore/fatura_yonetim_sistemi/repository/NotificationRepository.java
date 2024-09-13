package com.dicore.fatura_yonetim_sistemi.repository;

import com.dicore.fatura_yonetim_sistemi.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserId(Long userId);

    List<Notification> findUnreadNotificationsByUserId (Long userId);

}
