package com.tomzxy.webQuiz.controller;

import com.tomzxy.webQuiz.constants.EndPoint;
import com.tomzxy.webQuiz.dto.request.user.UserCreateRequestDTO;
import com.tomzxy.webQuiz.dto.request.user.UserUpdateRequest;
import com.tomzxy.webQuiz.dto.response.AppResponse.ResponseData;
import com.tomzxy.webQuiz.dto.response.AppResponse.ResponseError;
import com.tomzxy.webQuiz.dto.response.User.UserDetailResponse;
import com.tomzxy.webQuiz.exception.ResourceNotFoundException;
import com.tomzxy.webQuiz.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(EndPoint.User.BASE)
@Slf4j
@Validated
@Tag(name="User controller")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<Long> saveUser(@Valid @RequestBody UserCreateRequestDTO requestDTO){
        log.info("add user with userId: {}", requestDTO);

        try{
            Long userId = userService.saveUser(requestDTO);
            return new ResponseData<>(HttpStatus.CREATED.value(), "User has been saved", userId);
        }catch (Exception e){
            log.error("Error add user", e);
            return  new ResponseError(HttpStatus.BAD_REQUEST.value(), "User add failed");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseData<List<UserDetailResponse>> getAllUser(){
        var authentication= SecurityContextHolder.getContext().getAuthentication();

        log.info("Username {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return new ResponseData<>(HttpStatus.OK.value(), "Get all users", userService.getUsers());
    }

    @GetMapping(EndPoint.User.ID)
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<UserDetailResponse> getUser(@PathVariable String userId){
       log.info("Request get user detail userId {}",userId);
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Get user successfully",userService.getUser(userId));
        }catch (ResourceNotFoundException e){
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping(EndPoint.Auth.ME)
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<UserDetailResponse> getMyInfo(){
        log.info("Request get my info");
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Get my info successfully",userService.getMyInfo());
        }catch (ResourceNotFoundException e){
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @DeleteMapping(EndPoint.User.ID)
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<UserDetailResponse> deleteUser(@PathVariable Long userId){
        log.info("Request delete user {}", userId);
        try {
            userService.deleteUser(userId);
            return new ResponseData<>(HttpStatus.OK.value(), "Delete user successfully");
        }catch (ResourceNotFoundException e){
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }


    @PutMapping(EndPoint.User.ID)
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<UserDetailResponse> updateUser(@PathVariable Long userId, @Valid @RequestBody UserUpdateRequest request){
        log.info("Request update user {}", userId);
        try {

            return new ResponseData<>(HttpStatus.OK.value(), "Update user successfully",userService.updateUser(userId, request));
        }catch (ResourceNotFoundException e){
            log.error("errorMessage={}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
