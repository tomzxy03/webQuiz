package com.tomzxy.webQuiz.dto.response.User;

import com.tomzxy.webQuiz.model.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseUserResponse {
    Long id;
    String userName;
    String password;

    Set<Role> roles;
}
