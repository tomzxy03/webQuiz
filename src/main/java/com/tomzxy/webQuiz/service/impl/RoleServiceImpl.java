package com.tomzxy.webQuiz.service.impl;

import com.tomzxy.webQuiz.dto.request.role.RoleUpdateRequest;
import com.tomzxy.webQuiz.dto.response.Role.BaseRoleResponse;
import com.tomzxy.webQuiz.exception.ResourceNotFoundException;
import com.tomzxy.webQuiz.mapper.RoleMapper;
import com.tomzxy.webQuiz.model.Permission;
import com.tomzxy.webQuiz.model.Role;
import com.tomzxy.webQuiz.model.RolePermission;
import com.tomzxy.webQuiz.repository.PermissionRepository;
import com.tomzxy.webQuiz.repository.RolePermissionRepository;
import com.tomzxy.webQuiz.repository.RoleRepository;
import com.tomzxy.webQuiz.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final RoleMapper roleMapper;



    @Override
    public List<BaseRoleResponse> getAllRole() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    @Override
    public void addRolePermissionObject(Role role, Map<String, List<String>> objectPermission) {
        var role_user = roleRepository.findByName(role.getName()).orElseThrow(()->new ResourceNotFoundException("Role User not exists!!"));
        for(Map.Entry<String, List<String>> entry: objectPermission.entrySet()){
            String objectType = entry.getKey();
            List<String> permissions = entry.getValue();
            for(String permissionId: permissions){
                Permission permission = permissionRepository.findById(permissionId).orElseThrow(()-> new ResourceNotFoundException("Permission not found!"));
                RolePermission rolePermission = RolePermission.builder()
                        .role(role_user)
                        .permission(permission)
                        .objectType(objectType)
                        .build();

                rolePermissionRepository.save(rolePermission);
            }
        }
    }

    @Override
    public BaseRoleResponse getRoleById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Role not found!"));
        return roleMapper.toRoleResponse(role);
    }

    @Override
    public BaseRoleResponse getRoleByName(String name) {
        Role role = roleRepository.findByName(name).orElseThrow(()-> new ResourceNotFoundException("Role not found!"));
        return roleMapper.toRoleResponse(role);
    }

    @Override
    public BaseRoleResponse addRole(RoleUpdateRequest roleUpdateRequest) {
        Role role = roleMapper.toRole(roleUpdateRequest);
        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public BaseRoleResponse updateRole(Long id,RoleUpdateRequest request) {
        Role role = roleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Role not found!"));
        roleMapper.toRole(request);
        return roleMapper.toRoleResponse(roleRepository.save(role));
    }
}
