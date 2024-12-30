package com.tomzxy.webQuiz.service;

import com.tomzxy.webQuiz.dto.request.role.RoleUpdateRequest;
import com.tomzxy.webQuiz.dto.response.Role.BaseRoleResponse;
import com.tomzxy.webQuiz.model.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {
     List<BaseRoleResponse> getAllRole();
     void addRolePermissionObject(Role role, Map<String, List<String>> objectPermission);

     BaseRoleResponse getRoleById(Long id);
     BaseRoleResponse getRoleByName(String name);
     BaseRoleResponse addRole(RoleUpdateRequest role);
     void deleteRole(Long id);
     BaseRoleResponse updateRole(Long id, RoleUpdateRequest request);



}
