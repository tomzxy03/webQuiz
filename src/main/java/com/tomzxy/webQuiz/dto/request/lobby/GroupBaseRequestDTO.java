package com.tomzxy.webQuiz.dto.request.lobby;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;



@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupBaseRequestDTO {
    String title;

    int total_user;

    String code_invite;

    String user_create_name;


}
