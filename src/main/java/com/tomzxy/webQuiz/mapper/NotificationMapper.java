package com.tomzxy.webQuiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.tomzxy.webQuiz.dto.request.notification.NotificationUserRequestDTO;
import com.tomzxy.webQuiz.dto.response.Notification.NotificationResponse;
import com.tomzxy.webQuiz.dto.response.Notification.NotificationUserResponse;
import com.tomzxy.webQuiz.model.NotificationUser;
import com.tomzxy.webQuiz.model.Notifications;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    @Mapping(source = "notification.id", target = "notificationId")
    @Mapping(source = "notification.title", target = "title")
    @Mapping(source = "notification.context", target = "context")
    @Mapping(source = "notification.createdAt", target = "create_at")
    @Mapping(source = "notification.notificationType", target = "notification_type")
    @Mapping(source = "user.id", target = "userId")
    NotificationUserResponse toNotificationUserResponse(NotificationUser notificationUser);

    Notifications toNotifications(NotificationUserRequestDTO notificationUserRequestDTO);

    NotificationResponse toNotificationResponse(Notifications notification);

    @Mapping(target = "notifications", ignore = true)
    void updateNotifications(@MappingTarget Notifications notifications,
            NotificationUserRequestDTO notificationUserRequestDTO);
}
