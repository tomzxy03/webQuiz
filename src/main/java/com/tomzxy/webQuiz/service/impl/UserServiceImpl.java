package com.tomzxy.webQuiz.service.impl;

import com.tomzxy.webQuiz.constants.PredefinedRole;
import com.tomzxy.webQuiz.dto.request.user.UserCreateRequestDTO;
import com.tomzxy.webQuiz.dto.request.user.UserUpdateRequest;
import com.tomzxy.webQuiz.dto.response.AppResponse.PageResponse;
import com.tomzxy.webQuiz.dto.response.User.UserDetailResponse;
import com.tomzxy.webQuiz.exception.ResourceNotFoundException;
import com.tomzxy.webQuiz.mapper.UserMapper;
import com.tomzxy.webQuiz.model.Role;
import com.tomzxy.webQuiz.model.User;
import com.tomzxy.webQuiz.repository.RoleRepository;
import com.tomzxy.webQuiz.repository.UserRepository;
import com.tomzxy.webQuiz.service.UserService;
import com.tomzxy.webQuiz.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    /**
     * Save user
     * @param request
     * @return
     *
     * */
    @Override
    public Long saveUser(UserCreateRequestDTO request) {
        User user = userMapper.toUser(request);
        Set<Role> roles = new HashSet<>();
        roleRepository.findByName(PredefinedRole.USER_ROLE).ifPresent(roles::add);

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        try{
            userRepository.save(user);
            log.info("User has been save!");
        }catch (Exception e){
            log.error("Error creating user: ", e);
        }
        return user.getId();
    }




    /**
     * update user
     * @param userId
     * @param request
     * @return
     *
     * */
    @Override
    @PostAuthorize("returnObject.userName = authentication.name")
    public UserDetailResponse updateUser(long userId, UserUpdateRequest request) {
        log.info("Installing update user...");
        User user = getUserById(userId);
        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllByName(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));


    }


    @Override
    public void changeStatus(long userId, UserStatus status) {

    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDetailResponse getUser(String id) {
        return null;
    }


    /**
     * Get user by ID
     *
     * @param id
     * @return
     */
    @Override
    @PostAuthorize("returnObject.userName = authentication.name")
    public UserDetailResponse getUser(Long id) {
        User user =getUserById(id);
        return UserDetailResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .password( passwordEncoder.encode(user.getPassword()))
                .fullName(user.getFullName())
                .dob(user.getDob())
                .email(user.getEmail())
                .user_img(user.getUser_img())
                .status(user.getStatus())
                .phone(user.getPhone())
                .build();
    }

    /**
     * Get all user
     *
     * @return List<UserDetailResponse>
     */

    @Override
    public List<UserDetailResponse> getUsers() {
        log.info("In method get Users");
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();

    }

    public UserDetailResponse getMyInfo(){
        var context= SecurityContextHolder.getContext();
        String name= context.getAuthentication().getName();

        User user= userRepository.findByUserName(name).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        return userMapper.toUserResponse(user);
    }
    @Override
    public PageResponse<?> getAllUsersWithSortBy(int pageNo, int pageSize, String sortBy) {
        return null;


    }

    @Override
    public PageResponse<?> getAllUsersWithSortByMultipleColumns(int pageNo, int pageSize, String... sorts) {
        return null;
    }

    @Override
    public PageResponse<?> getAllUsersAndSearchWithPagingAndSorting(int pageNo, int pageSize, String search, String sortBy) {
        return null;
    }

    @Override
    public PageResponse<?> advanceSearchWithCriteria(int pageNo, int pageSize, String sortBy, String address, String... search) {
        return null;
    }

    @Override
    public PageResponse<?> advanceSearchWithSpecifications(Pageable pageable, String[] user, String[] address) {
        return null;
    }


    /**
     * Get user by ID
     *
     * @param userId
     * @return
     */

    private User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
    }

    /**
     * Convert Page<User> to PageResponse
     *
     * @param pageable
     * @return
     */
    public PageResponse<?> convertToPageResponse(Page<User> users, Pageable pageable){
        List<UserDetailResponse> response = users.stream().map(user -> UserDetailResponse.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .password(user.getPassword())
                .fullName(user.getFullName())
                .dob(user.getDob())
                .phone(user.getPhone())
                .email(user.getEmail())
                .user_img(user.getUser_img())
                .gender(user.getGender())
                .status(user.getStatus())
                .build()).toList();
        return PageResponse.builder()
                .page(pageable.getPageNumber())
                .size(pageable.getPageSize())
                .total(users.getTotalPages())
                .items(response)
                .build();
    }



}
