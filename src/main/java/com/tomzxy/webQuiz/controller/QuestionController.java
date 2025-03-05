package com.tomzxy.webQuiz.controller;

import com.tomzxy.webQuiz.config.Translator;
import com.tomzxy.webQuiz.constants.EndPoint;
import com.tomzxy.webQuiz.dto.request.answer.AnswerRequestDTO;
import com.tomzxy.webQuiz.dto.request.question.QuestionCreateRequestDTO;
import com.tomzxy.webQuiz.dto.request.question.QuestionUpdateRequestDTO;
import com.tomzxy.webQuiz.dto.request.user.UserCreateRequestDTO;
import com.tomzxy.webQuiz.dto.request.user.UserUpdateRequest;
import com.tomzxy.webQuiz.dto.response.AppResponse.ResponseData;
import com.tomzxy.webQuiz.dto.response.AppResponse.ResponseError;
import com.tomzxy.webQuiz.dto.response.Question.QuestionResponse;
import com.tomzxy.webQuiz.dto.response.User.UserDetailResponse;
import com.tomzxy.webQuiz.exception.ResourceNotFoundException;
import com.tomzxy.webQuiz.service.QuestionService;
import com.tomzxy.webQuiz.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
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


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN') or hasRole('CREATOR')")
    public ResponseData<List<QuestionResponse>> getAllQuestion(){
        log.info("get all question ");

        try{
            return new ResponseData<>(HttpStatus.CREATED.value(), Translator.toLocale("question.getAll.successfully"), questionService.getAllQuestion());
        }catch (ResourceNotFoundException e){
            log.error("Error get all question", e);
            return  new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping(EndPoint.Question.ID)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN') or hasRole('CREATOR')")
    public ResponseData<QuestionResponse> getQuestion(@PathVariable Long questionId){
        log.info("get question with questionId {}",questionId );

        try{
            return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("question.get.successfully"), questionService.getQuestionById(questionId));
        }catch (ResourceNotFoundException e){
            log.error("Error get question", e);
            return  new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PutMapping(EndPoint.Question.ID)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<QuestionResponse> updateQuestion(@PathVariable Long questionId, @Valid @RequestBody QuestionUpdateRequestDTO requestDTO){
        log.info("update question with questionId {}",questionId );

        try{
            return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("question.update.successfully"), questionService.updateQuestion(questionId, requestDTO));
        }catch (ResourceNotFoundException e){
            log.error("Error update question", e);
            return  new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @DeleteMapping(EndPoint.Question.ID)
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<Void> deleteQuestion(@PathVariable Long questionId){
        log.info("delete question with questionId {}",questionId );

        try{
            questionService.deleteQuestion(questionId);
            return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("question.delete.successfully"));
        }catch (ResourceNotFoundException e){
            log.error("Error delete question", e);
            return  new ResponseError(HttpStatus.BAD_REQUEST.value(),e.getMessage());
        }
    }


    @PostMapping(EndPoint.Question.LEVEL)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<QuestionResponse> changeLevel(@PathVariable Long questionId, @RequestBody String level){
        log.info("change level question with questionId: {}", questionId);

        try{

            return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("question.add.successfully"), questionService.changeLevel(questionId,level));
        }catch (ResourceNotFoundException e){
            log.error("Error add question", e);
            return  new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PostMapping(EndPoint.Question.ADD_ANSWER)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<QuestionResponse> addAnswer(@PathVariable Long questionId, @RequestBody AnswerRequestDTO answerRequestDTO){
        log.info("add answer with questionId: {} {}", questionId, answerRequestDTO.getAnswerText());

        try{

            return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("answer.add.successfully"), questionService.addAnswer(questionId,answerRequestDTO));
        }catch (ResourceNotFoundException e){
            log.error("Error add answer", e);
            return  new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }


}
