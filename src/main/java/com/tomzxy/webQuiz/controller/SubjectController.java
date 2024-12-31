package com.tomzxy.webQuiz.controller;


import com.tomzxy.webQuiz.constants.EndPoint;
import com.tomzxy.webQuiz.dto.request.chapter.ChapterRequestDTO;
import com.tomzxy.webQuiz.dto.request.subject.SubjectRequest;
import com.tomzxy.webQuiz.dto.request.subject.SubjectUpdateRequest;
import com.tomzxy.webQuiz.dto.response.AppResponse.ResponseData;
import com.tomzxy.webQuiz.dto.response.AppResponse.ResponseError;
import com.tomzxy.webQuiz.dto.response.Chapter.ChapterResponse;
import com.tomzxy.webQuiz.dto.response.Subject.SubjectResponse;
import com.tomzxy.webQuiz.service.SubjectService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(EndPoint.Subject.BASE)
@Slf4j
@Validated
@Tag(name="Subject controller")
@RequiredArgsConstructor

public class SubjectController {
    private final SubjectService subjectService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<Long> addSubject(@RequestBody @Valid SubjectRequest subjectRequest){
        log.info("add subject with subjectId {}", subjectRequest);
        var subjectId = subjectService.addSubject(subjectRequest);
        try{
            return new ResponseData<>(HttpStatus.CREATED.value(), "", subjectId.getId());
        }catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "");
        }
    }

    @PostMapping(EndPoint.Subject.ADD_CHAPTER)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<ChapterResponse> addChapter(@PathVariable Long subjectId , @RequestBody @Valid ChapterRequestDTO chapterRequest){
        log.info("add chapter with subjectId and chapterId {} {}", subjectId, chapterRequest);
        var chapter= subjectService.addChapter(subjectId, chapterRequest);
        try{
            return new ResponseData<>(HttpStatus.CREATED.value(), "", chapter);
        }catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "");
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<List<SubjectResponse>> getAllSubject(){
        log.info("get all subject");
        try{
            return new ResponseData<>(HttpStatus.OK.value(), "", subjectService.getAllSubject());
        }catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "");
        }
    }

    @GetMapping(EndPoint.Subject.ID)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<SubjectResponse> getSubject(@PathVariable Long id){
        log.info("get subject by id {}", id);
        try{
            return new ResponseData<>(HttpStatus.OK.value(), "", subjectService.getSubjectById(id));
        }catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "");
        }
    }

    @PutMapping(EndPoint.Subject.ID)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<SubjectResponse> updateSubject(@PathVariable Long id, @RequestBody @Valid SubjectUpdateRequest subjectRequest ){
        log.info("update subject by id {}", id);
        try{
            return new ResponseData<>(HttpStatus.OK.value(), "", subjectService.updateSubject(id, subjectRequest));
        }catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "");
        }
    }

    @DeleteMapping(EndPoint.Subject.ID)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<Void> deleteSubject(@PathVariable Long id){
        log.info("delete subject by id {}", id);
        try{
            subjectService.deleteSubject(id);
            return new ResponseData<>(HttpStatus.OK.value(), "");
        }catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(),"");
        }
    }
}
