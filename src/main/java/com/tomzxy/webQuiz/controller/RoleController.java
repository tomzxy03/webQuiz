package com.tomzxy.webQuiz.controller;

import com.tomzxy.webQuiz.constants.EndPoint;
import com.tomzxy.webQuiz.dto.request.role.RoleUpdateRequest;
import com.tomzxy.webQuiz.dto.response.AppResponse.ResponseData;
import com.tomzxy.webQuiz.dto.response.AppResponse.ResponseError;
import com.tomzxy.webQuiz.dto.response.Role.BaseRoleResponse;
import com.tomzxy.webQuiz.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(EndPoint.Role.BASE)
@Slf4j
@Validated
@Tag(name="User controller")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize( "hasRole('ADMIN') and @permissionService.hasPermission(role,'CREATE')")

    public ResponseData<BaseRoleResponse> saveRole(@RequestBody @Valid RoleUpdateRequest request){
        log.info("add role with roleId: {}", request);

        try {
            return new ResponseData<>(HttpStatus.CREATED.value(), "add role successfully", roleService.addRole(request));
        }
        catch (Exception e){
            log.error("Error add role", e);
            return  new ResponseError(HttpStatus.BAD_REQUEST.value(), "Role add failed");
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize( "hasRole('ADMIN')")
    public ResponseData<List<BaseRoleResponse>> getAllRoles(){
        log.info("get all roles");
        try {
            return new ResponseData<>(HttpStatus.OK.value(),"", roleService.getAllRole());
        }
        catch (Exception e){
            log.error("Error get all roles", e);
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "");
        }
    }
}
