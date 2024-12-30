package com.tomzxy.webQuiz.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequestDTO implements Serializable {
    @NotBlank(message = "Full name must be not blank") // khong cho phep rong
    String fullName;

    @Email(message = "Email invalid format") // chi chap nhan gia tri dang email
    String email;


    @NotBlank(message = "User name must be not blank")
    String userName;

    @NotBlank(message = "User name must be not blank")
    String password;



}
