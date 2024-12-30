package com.tomzxy.webQuiz.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Level {
    @JsonProperty("hard")
    HARD,
    @JsonProperty("medium")
    MEDIUM,
    @JsonProperty("easy")
    EASY;
}
