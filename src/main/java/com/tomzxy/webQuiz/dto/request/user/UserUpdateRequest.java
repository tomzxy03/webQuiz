package com.tomzxy.webQuiz.dto.request.user;

import com.tomzxy.webQuiz.enums.Gender;
import com.tomzxy.webQuiz.validator.EnumPattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest implements Serializable {

    String userName;

    String fullName;

    String password;

    LocalDate dob;

    @EnumPattern(name = "gender", regexp = "MALE|FEMALE")
    Gender gender;

    List<String> roles;
}
