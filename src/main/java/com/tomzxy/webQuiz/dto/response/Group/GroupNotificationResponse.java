package com.tomzxy.webQuiz.dto.response.Group;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupNotificationResponse {
    String title;

    int total_user;

    String code_invite;

    String creator_userName;

}
