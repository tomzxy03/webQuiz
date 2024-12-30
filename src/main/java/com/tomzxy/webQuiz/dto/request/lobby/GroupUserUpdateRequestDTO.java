package com.tomzxy.webQuiz.dto.request.lobby;

import com.tomzxy.webQuiz.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupUserUpdateRequestDTO {

    List<Long> userIds;


}
