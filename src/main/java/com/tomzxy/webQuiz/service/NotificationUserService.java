package com.tomzxy.webQuiz.service;

import com.tomzxy.webQuiz.dto.request.notification.NotificationUserRequestDTO;
import com.tomzxy.webQuiz.dto.response.AppResponse.PageResponse;
import com.tomzxy.webQuiz.dto.response.Notification.NotificationResponse;
import com.tomzxy.webQuiz.dto.response.Notification.NotificationUserResponse;

public interface NotificationUserService {
    NotificationResponse addNotificationUser(NotificationUserRequestDTO requestDTO);

    NotificationUserResponse getNotificationUserById(Long notificationUserId, Long userId);

    NotificationUserResponse updateNotificationUser(Long notificationUserId,
            NotificationUserRequestDTO notificationUserRequestDTO);

    void deleteNotificationUser(Long notificationUserId);

    PageResponse<?> getAllNotificationUsersOrdered( int page, int size);

}
