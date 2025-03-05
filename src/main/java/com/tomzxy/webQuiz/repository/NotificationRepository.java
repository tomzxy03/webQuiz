package com.tomzxy.webQuiz.repository;

import javax.management.Notification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tomzxy.webQuiz.dto.response.Notification.NotificationUserResponse;
import com.tomzxy.webQuiz.enums.NotificationType;
import com.tomzxy.webQuiz.model.Notifications;

@Repository
public interface NotificationRepository extends JpaRepository<Notifications, Long> {
//    Page<Notification> findByNotificationTypeWithSort(NotificationType type, Pageable pageable);
//
//    Page<NotificationUserResponse> findAllNotificationUsers(NotificationType notificationType, Pageable pageable);

}
