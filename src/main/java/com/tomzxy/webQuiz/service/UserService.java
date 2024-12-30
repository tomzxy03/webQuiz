package com.tomzxy.webQuiz.service;

import com.tomzxy.webQuiz.dto.request.user.UserCreateRequestDTO;
import com.tomzxy.webQuiz.dto.request.user.UserUpdateRequest;
import com.tomzxy.webQuiz.dto.response.PageResponse;
import com.tomzxy.webQuiz.dto.response.User.UserDetailResponse;
import com.tomzxy.webQuiz.enums.UserStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;

import java.util.List;

public interface UserService {
    Long saveUser(UserCreateRequestDTO request);



    @PostAuthorize("returnObject.userName = authentication.name")
    UserDetailResponse updateUser(long userId, UserUpdateRequest request);

    void changeStatus(long userId, UserStatus status);
    void deleteUser(long userId);
    UserDetailResponse getUser(String id);

    UserDetailResponse getUser(Long id);

    List<UserDetailResponse> getUsers();

    UserDetailResponse getMyInfo();

    PageResponse<?> getAllUsersWithSortBy(int pageNo, int pageSize, String sortBy);

    PageResponse<?> getAllUsersWithSortByMultipleColumns(int pageNo, int pageSize, String... sorts);

    PageResponse<?> getAllUsersAndSearchWithPagingAndSorting(int pageNo, int pageSize, String search, String sortBy);

    PageResponse<?> advanceSearchWithCriteria(int pageNo, int pageSize, String sortBy, String address, String... search);

    PageResponse<?> advanceSearchWithSpecifications(Pageable pageable, String[] user, String[] address);
}
