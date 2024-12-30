package com.tomzxy.webQuiz.mapper;

import com.tomzxy.webQuiz.dto.request.permission.PermissionRequest;
import com.tomzxy.webQuiz.dto.request.role.RoleUpdateRequest;
import com.tomzxy.webQuiz.dto.response.Permission.PermissionResponse;
import com.tomzxy.webQuiz.dto.response.Role.BaseRoleResponse;
import com.tomzxy.webQuiz.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    BaseRoleResponse toRoleResponse(Role role);

    @Mapping(target = "rolePermissions", ignore = true)
    void updateRole(@MappingTarget Role role,  RoleUpdateRequest roleUpdateRequest);

    @Mapping(target = "rolePermissions", ignore = true)
    Role toRole(RoleUpdateRequest roleUpdateRequest);
}
