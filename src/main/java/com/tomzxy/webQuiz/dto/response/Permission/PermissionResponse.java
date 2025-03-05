package com.tomzxy.webQuiz.dto.response.Permission;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PermissionResponse {
    String name;
    String description;
}
