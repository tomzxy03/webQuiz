package com.tomzxy.webQuiz.controller;


import com.tomzxy.webQuiz.config.Translator;
import com.tomzxy.webQuiz.constants.EndPoint;
import com.tomzxy.webQuiz.dto.request.answer.AnswerRequestDTO;
import com.tomzxy.webQuiz.dto.response.Answer.AnswerResponse;
import com.tomzxy.webQuiz.dto.response.AppResponse.ResponseData;
import com.tomzxy.webQuiz.dto.response.AppResponse.ResponseError;
import com.tomzxy.webQuiz.exception.ResourceNotFoundException;
import com.tomzxy.webQuiz.service.AnswerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(EndPoint.Answer.BASE)
@Validated
@Tag(name="Answer controller")
public class AnswerController {
    private final AnswerService answerService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<List<AnswerResponse>> getAllAnswer(){
        log.info("Get all Answer ");
        try{
            return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("answer.getAll.successfully"), answerService.getAllAnswer());
        }catch (ResourceNotFoundException e){
            log.error("Error get answer {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping(EndPoint.Answer.ID)
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<AnswerResponse> getAnswer(@PathVariable Long answerId){
        log.info("Get answer by answerId {} ", answerId);
        try{
            return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("answer.get.successfully"), answerService.getAnswerById(answerId));
        }catch (ResourceNotFoundException e){
            log.error("Error get answer {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }


    @PutMapping(EndPoint.Answer.ID)
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<AnswerResponse> updateAnswer(@PathVariable Long answerId, @Valid @RequestBody AnswerRequestDTO answerRequestDTO){
        log.info("Update answer by answerId {} ", answerId);
        try{
            return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("answer.update.successfully"), answerService.updateAnswer(answerId,answerRequestDTO));
        }catch (ResourceNotFoundException e){
            log.error("Error update answer {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @DeleteMapping(EndPoint.Answer.ID)
    @ResponseStatus(HttpStatus.OK)
    public ResponseData<AnswerResponse> deleteAnswer(@PathVariable Long answerId){
        log.info("Delete answer by answerId {} ", answerId);
        try{
            answerService.deleteAnswer(answerId);
            return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("answer.delete.successfully"));
        }catch (ResourceNotFoundException e){
            log.error("Error delete answer {}", e.getMessage(), e.getCause());
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
