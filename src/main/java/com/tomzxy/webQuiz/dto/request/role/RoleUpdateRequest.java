package com.tomzxy.webQuiz.dto.request.role;


import com.tomzxy.webQuiz.model.RolePermission;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class RoleUpdateRequest {

    @NotBlank(message = "role name must be not blank")
    String name;

    Set<RolePermission> rolePermission;

}
