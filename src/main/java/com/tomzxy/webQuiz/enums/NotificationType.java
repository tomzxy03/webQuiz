package com.tomzxy.webQuiz.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum NotificationType {
    @JsonProperty(value = "group")
    GROUP ,
    @JsonProperty(value = "website")
    WEBSITE
}
