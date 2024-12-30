package com.tomzxy.webQuiz.mapper;

import com.tomzxy.webQuiz.dto.request.permission.PermissionRequest;
import com.tomzxy.webQuiz.dto.request.user.UserUpdateRequest;
import com.tomzxy.webQuiz.dto.response.Permission.PermissionResponse;
import com.tomzxy.webQuiz.dto.response.User.UserDetailResponse;
import com.tomzxy.webQuiz.model.Permission;
import com.tomzxy.webQuiz.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionResponse toPermissionResponse(Permission permission);


    @Mapping(target = "rolePermissions", ignore = true)
    Permission toPermission(PermissionRequest permissionRequest);
}
