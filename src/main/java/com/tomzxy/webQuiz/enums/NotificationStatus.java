package com.tomzxy.webQuiz.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum NotificationStatus {
    @JsonProperty(value = "read")
    READ,
    @JsonProperty(value = "unread")
    UNREAD
}
