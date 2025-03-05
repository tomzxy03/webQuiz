package com.tomzxy.webQuiz.dto.response.Group;

import com.tomzxy.webQuiz.dto.response.User.BaseUserResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupMembersResponse {
    int total_user;

    List<BaseUserResponse> users;

}
