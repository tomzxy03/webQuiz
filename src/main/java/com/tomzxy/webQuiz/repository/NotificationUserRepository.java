package com.tomzxy.webQuiz.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tomzxy.webQuiz.dto.response.Notification.NotificationUserResponse;
import com.tomzxy.webQuiz.enums.NotificationType;
import com.tomzxy.webQuiz.model.NotificationUser;

@Repository
public interface NotificationUserRepository
                extends JpaRepository<NotificationUser, Long>, JpaSpecificationExecutor<NotificationUser> {

        Optional<NotificationUser> findByNotificationIdAndUserId(Long notificationId, Long userId);

        Optional<NotificationUser> findByNotificationId(Long notificationId);

        @Query(
                value = "SELECT nu FROM NotificationUser nu " +
                        "JOIN FETCH nu.notification n " +
                        "JOIN FETCH nu.user u " +
                        "WHERE n.notificationType = :type " +
                        "AND u.userName = :userName " + // Sử dụng user.id từ entity User
                        "ORDER BY n.createdAt DESC",
                countQuery = "SELECT COUNT(nu) FROM NotificationUser nu " +
                        "JOIN nu.notification n " +
                        "WHERE n.notificationType = :type " +
                        "AND nu.user.userName = :userName"
        )
        Page<NotificationUser> findByNotificationTypeSortByCreatedAt(
                @Param("type") NotificationType type,
                @Param("userName") String userName,
                Pageable pageable);

        @Query(
                value = "SELECT NEW com.tomzxy.webQuiz.dto.response.Notification.NotificationUserResponse( " +
                        "n.id, n.title, n.context, n.notificationType, nu.status, " +
                        "n.createdAt, nu.readAt, u.id) " + // Sử dụng trực tiếp Date
                        "FROM NotificationUser nu " +
                        "JOIN nu.notification n " +
                        "JOIN nu.user u " +
                        "WHERE n.notificationType = :type " +
                        "AND u.id = :userId " +
                        "ORDER BY n.createdAt DESC",
                countQuery = "SELECT COUNT(nu) FROM NotificationUser nu " +
                        "JOIN nu.notification n " +
                        "WHERE n.notificationType = :type " +
                        "AND nu.user.id = :userId"
        )
        Page<NotificationUserResponse> findNotificationsByTypeAndUser(
                @Param("type") NotificationType type,
                @Param("userId") Long userId,
                Pageable pageable
        );
}
