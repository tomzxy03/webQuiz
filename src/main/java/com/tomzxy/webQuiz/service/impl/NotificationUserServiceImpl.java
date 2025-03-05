package com.tomzxy.webQuiz.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.tomzxy.webQuiz.config.SecurityUtils;
import lombok.NonNull;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tomzxy.webQuiz.dto.request.notification.NotificationUserRequestDTO;
import com.tomzxy.webQuiz.dto.response.AppResponse.PageResponse;
import com.tomzxy.webQuiz.dto.response.Notification.NotificationResponse;
import com.tomzxy.webQuiz.dto.response.Notification.NotificationUserResponse;
import com.tomzxy.webQuiz.enums.NotificationStatus;
import com.tomzxy.webQuiz.enums.NotificationType;
import com.tomzxy.webQuiz.exception.ResourceNotFoundException;
import com.tomzxy.webQuiz.mapper.NotificationMapper;
import com.tomzxy.webQuiz.model.NotificationUser;
import com.tomzxy.webQuiz.model.Notifications;
import com.tomzxy.webQuiz.model.User;
import com.tomzxy.webQuiz.repository.NotificationRepository;
import com.tomzxy.webQuiz.repository.NotificationUserRepository;
import com.tomzxy.webQuiz.repository.UserRepository;
import com.tomzxy.webQuiz.service.NotificationUserService;

import com.tomzxy.webQuiz.config.AsyncConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationUserServiceImpl implements NotificationUserService {


    private final NotificationUserRepository notificationUserRepository;
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final UserRepository userRepository;


    @Qualifier("taskExecutor")
    private final  ThreadPoolTaskExecutor taskExecutor;
    @Override
    @Transactional
    public NotificationResponse addNotificationUser(NotificationUserRequestDTO requestDTO) {
        Notifications notifications = notificationMapper.toNotifications(requestDTO);
        notifications.setNotificationType(NotificationType.WEBSITE);
        notificationRepository.save(notifications);

        NotificationResponse response = notificationMapper.toNotificationResponse(notifications);

        taskExecutor.execute(() -> {
            try {
                processNotificationUsersAsync(notifications);
            } catch (Exception e) {
                log.error("Async processing failed for notification {}: {}",
                        notifications.getId(), e.getMessage());
            }
        });
        return response;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW) // Transaction riêng
    public void processNotificationUsersAsync(Notifications notification) {
        int page = 0;
        int pageSize = 500; // Batch size
        PageRequest pageRequest;
        Page<User> userPage;

        do {
            pageRequest = PageRequest.of(page, pageSize);
            userPage = userRepository.findAll(pageRequest);
            List<NotificationUser> batch = new ArrayList<>();

            for (User user : userPage.getContent()) {
                NotificationUser nu = new NotificationUser();
                nu.setNotification(notification);
                nu.setUser(user);
                nu.setStatus(NotificationStatus.UNREAD);
                batch.add(nu);
            }

            notificationUserRepository.saveAll(batch); // Lưu batch
            log.info("Saved batch {} for notification {}", page + 1, notification.getId());
            page++;
        } while (userPage.hasNext());
    }

    // mapping notification and user to notificationUser
    private NotificationUser createNotificationUser(Notifications notification, User user) {
        NotificationUser notificationUser = new NotificationUser();
        notificationUser.setNotification(notification);
        notificationUser.setUser(user);
        notificationUser.setStatus(NotificationStatus.UNREAD);
        notificationUser.setReadAt(null);
        return notificationUser;
    }

    @Override
    @Transactional
    public NotificationUserResponse getNotificationUserById(Long notificationUserId, Long userId) {
        NotificationUser notificationUser = notificationUserRepository
                .findByNotificationIdAndUserId(notificationUserId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found for user id: " + userId));
        // Nếu trạng thái là UNREAD thì cập nhật thành READ
        if ("unread".equalsIgnoreCase(notificationUser.getStatus().toString())) {
            notificationUser.setStatus(NotificationStatus.READ);
            notificationUserRepository.save(notificationUser);
        }
        return notificationMapper.toNotificationUserResponse(notificationUser);
    }

    @Override
    public NotificationUserResponse updateNotificationUser(Long notificationUserId,
            NotificationUserRequestDTO notificationUserRequestDTO) {
        NotificationUser notificationUser = notificationUserRepository.findByNotificationId(notificationUserId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "NotificationUser not found with id " + notificationUserId));
        notificationUser.setStatus(NotificationStatus.UNREAD);
        notificationUser.setReadAt(null);

        notificationMapper.updateNotifications(notificationUser.getNotification(), notificationUserRequestDTO);
        notificationUserRepository.save(notificationUser);
        return notificationMapper.toNotificationUserResponse(notificationUser);

    }

    @Override
    public void deleteNotificationUser(Long notificationUserId) {
        notificationUserRepository.deleteById(notificationUserId);
    }

    /**
     * Get all notification users ordered by created date
     * 
     *
     * @param page
     * @param size
     * 
     * @return
     */
    @Override
    public PageResponse<?> getAllNotificationUsersOrdered(int page, int size) {
        String userName = SecurityUtils.getUserNameFromCurrentJwt();
        System.out.println(userName);
        Pageable pageable = PageRequest.of(page, size);
        Page<NotificationUser> notificationUsers = notificationUserRepository

                .findByNotificationTypeSortByCreatedAt(NotificationType.WEBSITE, userName, pageable);
        return convertPageResponse(notificationUsers, pageable);

    }

    public PageResponse<?> convertPageResponse(Page<NotificationUser> notification, Pageable pageable) {
        List<NotificationUserResponse> notificationUsers = notification.getContent().stream()
                .map(notificationMapper::toNotificationUserResponse)
                .collect(Collectors.toList());
        return PageResponse.<List<NotificationUserResponse>>builder()
                .page(pageable.getPageNumber())
                .size(pageable.getPageSize())
                .total(notification.getTotalElements())
                .items(notificationUsers)
                .build();
    }

}