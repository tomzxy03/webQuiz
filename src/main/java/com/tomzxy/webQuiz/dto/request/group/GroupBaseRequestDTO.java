package com.tomzxy.webQuiz.dto.request.group;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;



@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupBaseRequestDTO {
    String title;

    String code_invite;
}
