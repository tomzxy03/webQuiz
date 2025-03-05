package com.tomzxy.webQuiz.dto.response.Notification;

import java.time.LocalDate;

import com.tomzxy.webQuiz.enums.NotificationStatus;
import com.tomzxy.webQuiz.enums.NotificationType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class NotificationResponse {
    Long id;
    String title;

    String context;

    NotificationType notificationType;

    NotificationStatus status;

    LocalDate create_at;

    LocalDate readAt;

    Long totalOfUser;

}
