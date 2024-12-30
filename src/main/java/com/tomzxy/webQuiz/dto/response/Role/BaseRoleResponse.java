package com.tomzxy.webQuiz.dto.response.Role;

import com.tomzxy.webQuiz.model.RolePermission;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseRoleResponse {
    String name;
    Set<RolePermission> rolePermissionList;

}
