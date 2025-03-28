package com.tomzxy.webQuiz.dto.request.group;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupNotifyUpdateRequestDTO {

    List<Long> notificationIds;


}
