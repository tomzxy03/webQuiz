package com.tomzxy.webQuiz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.tomzxy.webQuiz.config.Translator;
import com.tomzxy.webQuiz.constants.EndPoint;
import com.tomzxy.webQuiz.dto.request.notification.NotificationUserRequestDTO;
import com.tomzxy.webQuiz.dto.response.AppResponse.PageResponse;
import com.tomzxy.webQuiz.dto.response.AppResponse.ResponseData;
import com.tomzxy.webQuiz.dto.response.AppResponse.ResponseError;
import com.tomzxy.webQuiz.dto.response.Notification.NotificationResponse;
import com.tomzxy.webQuiz.service.NotificationUserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(EndPoint.Notification.BASE)
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name = "Notification controller")
public class NotificationController {

    private final NotificationUserService notificationService;

    @PostMapping()
    public ResponseData<NotificationResponse> addNotification(
            @RequestBody @Valid NotificationUserRequestDTO requestDTO) {
        log.info("add notification with notificationId: {}", requestDTO);
        try {
            return new ResponseData<>(HttpStatus.CREATED.value(), Translator.toLocale("notification.add.successfully"),
                    notificationService.addNotificationUser(requestDTO));
        } catch (Exception e) {
            log.error("Error add notification", e);
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    // get all notifications
    @GetMapping()
    public ResponseData<PageResponse<?>> getAllNotification( @RequestParam int page,
            @RequestParam int size) {
        log.info("get all notifications");
        try {


            return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("notification.get.successfully"),
                    notificationService.getAllNotificationUsersOrdered( page, size));
        } catch (Exception e) {
            log.error("Error get all notifications", e);
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }

    }
    // get notification by id
    @GetMapping(EndPoint.Notification.ID)
    public ResponseData<?> getNotificationById(@PathVariable Long notificationId, @RequestParam Long userId) {
        log.info("get notification by id with notificationId: {}", notificationId);
        try {
            return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("notification.get.successfully"),
                    notificationService.getNotificationUserById(notificationId, userId));
        } catch (Exception e) {
            log.error("Error get notification by id", e);
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    // update notification
    @PutMapping(EndPoint.Notification.ID)
    public ResponseData<?> updateNotification(@RequestParam Long notificationUserId,
            @RequestBody @Valid NotificationUserRequestDTO requestDTO) {
        log.info("update notification with notificationUserId: {}", notificationUserId);
        try {
            return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("notification.update.successfully"),
                    notificationService.updateNotificationUser(notificationUserId, requestDTO));
        } catch (Exception e) {
            log.error("Error update notification", e);
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    // delete notification
    @DeleteMapping(EndPoint.Notification.ID)
    public ResponseData<?> deleteNotification(@RequestParam Long notificationUserId) {
        log.info("delete notification with notificationUserId: {}", notificationUserId);
        try {
            notificationService.deleteNotificationUser(notificationUserId);
            return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("notification.delete.successfully"));
        } catch (Exception e) {
            log.error("Error delete notification", e);
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

}
