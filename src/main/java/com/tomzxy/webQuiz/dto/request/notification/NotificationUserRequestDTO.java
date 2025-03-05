package com.tomzxy.webQuiz.dto.request.notification;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationUserRequestDTO {
    String title;

    String context;
}
