package com.tomzxy.webQuiz.controller;

import com.tomzxy.webQuiz.config.Translator;
import com.tomzxy.webQuiz.constants.EndPoint;

import com.tomzxy.webQuiz.dto.request.quiz.basic.QuizBasicCreateRequestDTO;
import com.tomzxy.webQuiz.dto.request.quiz.QuizUpdateRequestDTO;
import com.tomzxy.webQuiz.dto.response.AppResponse.PageResponse;
import com.tomzxy.webQuiz.dto.response.AppResponse.ResponseData;
import com.tomzxy.webQuiz.dto.response.AppResponse.ResponseError;
import com.tomzxy.webQuiz.dto.response.Quiz.QuizResponse;
import com.tomzxy.webQuiz.service.QuizService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EndPoint.Quiz.BASE)
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name = "Quiz controller")
public class QuizController {

    private final QuizService quizService;

    @PostMapping()
    public ResponseData<QuizResponse> addQuiz( @PathVariable Long chapterId,
            @RequestBody @Valid QuizBasicCreateRequestDTO requestDTO) {
        log.info("add Quiz with QuizId: {}", requestDTO);
        try {
            return new ResponseData<>(HttpStatus.CREATED.value(), Translator.toLocale("Quiz.add.successfully"),
                    quizService.addQuiz(chapterId,requestDTO));
        } catch (Exception e) {
            log.error("Error add Quiz", e);
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    // get all Quizs
    @GetMapping()
    public ResponseData<PageResponse<?>> getAllQuiz( @RequestParam int page,
            @RequestParam int size) {
        log.info("get all Quizs");
        try {


            return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("Quiz.get.successfully"),
                    quizService.getAllQuizWithPage( page, size));
        } catch (Exception e) {
            log.error("Error get all Quizs", e);
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }

    }
    // get Quiz by id
    @GetMapping(EndPoint.Quiz.ID)
    public ResponseData<?> getQuizById(@PathVariable Long QuizId) {
        log.info("get Quiz by id with QuizId: {}", QuizId);
        try {
            return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("Quiz.get.successfully"),
                    quizService.getQuizById(QuizId));
        } catch (Exception e) {
            log.error("Error get Quiz by id", e);
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    // update Quiz
    @PutMapping(EndPoint.Quiz.ID)
    public ResponseData<?> updateQuiz(@RequestParam Long QuizId,
            @RequestBody @Valid QuizUpdateRequestDTO requestDTO) {
        log.info("update Quiz with QuizId: {}", QuizId);
        try {
            return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("Quiz.update.successfully"),
                    quizService.updateQuiz(QuizId, requestDTO));
        } catch (Exception e) {
            log.error("Error update Quiz", e);
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    // delete Quiz
    @DeleteMapping(EndPoint.Quiz.ID)
    public ResponseData<?> deleteQuiz(@RequestParam Long QuizId) {
        log.info("delete Quiz with QuizId: {}", QuizId);
        try {
            quizService.deleteQuiz(QuizId);
            return new ResponseData<>(HttpStatus.OK.value(), Translator.toLocale("Quiz.delete.successfully"));
        } catch (Exception e) {
            log.error("Error delete Quiz", e);
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

}
