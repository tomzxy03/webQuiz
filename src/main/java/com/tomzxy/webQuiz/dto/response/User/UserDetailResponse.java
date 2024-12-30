package com.tomzxy.webQuiz.dto.response.User;

import com.tomzxy.webQuiz.enums.Gender;
import com.tomzxy.webQuiz.enums.UserStatus;
import lombok.*;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDetailResponse implements Serializable {
    Long id;
    String fullName;
    String email;
    String phone;
    String userName;
    String password;
    LocalDate dob;
    Gender gender;
    UserStatus status;
    Blob user_img;
//    List<Role> roles;

}
