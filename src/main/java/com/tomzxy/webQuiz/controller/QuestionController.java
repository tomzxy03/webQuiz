package com.tomzxy.webQuiz.controller;

import com.tomzxy.webQuiz.constants.EndPoint;
import com.tomzxy.webQuiz.dto.request.answer.AnswerRequestDTO;
import com.tomzxy.webQuiz.dto.request.question.QuestionCreateRequestDTO;
import com.tomzxy.webQuiz.dto.request.user.UserCreateRequestDTO;
import com.tomzxy.webQuiz.dto.request.user.UserUpdateRequest;
import com.tomzxy.webQuiz.dto.response.AppResponse.ResponseData;
import com.tomzxy.webQuiz.dto.response.AppResponse.ResponseError;
import com.tomzxy.webQuiz.dto.response.Question.QuestionResponse;
import com.tomzxy.webQuiz.dto.response.User.UserDetailResponse;
import com.tomzxy.webQuiz.exception.ResourceNotFoundException;
import com.tomzxy.webQuiz.service.QuestionService;
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
@RequestMapping(EndPoint.Question.BASE)
@Slf4j
@Validated
@Tag(name="Question controller")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<QuestionResponse> saveQuestion(@Valid @RequestBody QuestionCreateRequestDTO requestDTO){
        log.info("add question with questionId: {} {}", requestDTO.getQuestionText(), requestDTO.getAnswers().stream().map(AnswerRequestDTO::getAnswer_text).toList());

        try{

            return new ResponseData<>(HttpStatus.CREATED.value(), "Question has been saved", questionService.addQuestion(requestDTO));
        }catch (Exception e){
            log.error("Error add question", e);
            return  new ResponseError(HttpStatus.BAD_REQUEST.value(), "Question add failed");
        }
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<List<QuestionResponse>> getAllQuestion(){
        log.info("get all question ");

        try{
            return new ResponseData<>(HttpStatus.CREATED.value(), "Get all question", questionService.getAllQuestion());
        }catch (Exception e){
            log.error("Error add question", e);
            return  new ResponseError(HttpStatus.BAD_REQUEST.value(), "Question add failed");
        }
    }



}
