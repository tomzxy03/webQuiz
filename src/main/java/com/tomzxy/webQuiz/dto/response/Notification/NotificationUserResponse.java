package com.tomzxy.webQuiz.dto.response.Notification;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tomzxy.webQuiz.enums.NotificationStatus;
import com.tomzxy.webQuiz.enums.NotificationType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationUserResponse {
    Long notificationId;

    String title;

    String context;

    NotificationType notification_type;

    NotificationStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    Date create_at;

    LocalDate readAt;
    Long userId;

    public NotificationUserResponse(
            Long notificationId,
            String title,
            String context,
            NotificationType notificationType,
            NotificationStatus status,
            Date create_at, // Kiểu Date
            LocalDate readAt,
            Long userId
    ) {
        this.notificationId = notificationId;
        this.title = title;
        this.context = context;
        this.notification_type = notificationType;
        this.status = status;
        this.create_at = create_at;
        this.readAt = readAt;
        this.userId = userId;
    }

}
