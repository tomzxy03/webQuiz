package com.tomzxy.webQuiz.controller;

import com.tomzxy.webQuiz.constants.EndPoint;
import com.tomzxy.webQuiz.dto.request.chapter.ChapterRequestDTO;
import com.tomzxy.webQuiz.dto.request.subject.SubjectUpdateRequest;
import com.tomzxy.webQuiz.dto.response.AppResponse.ResponseData;
import com.tomzxy.webQuiz.dto.response.AppResponse.ResponseError;
import com.tomzxy.webQuiz.dto.response.Chapter.ChapterResponse;
import com.tomzxy.webQuiz.dto.response.Subject.SubjectResponse;
import com.tomzxy.webQuiz.service.ChapterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(EndPoint.Chapter.BASE)
@Slf4j
@Validated
@Tag(name="Chapter controller")
@RequiredArgsConstructor
public class ChapterController {

    private final ChapterService chapterService;

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<List<ChapterResponse>> getAllChapter(){
        log.info("get all chapter ");
        try{
            return new ResponseData<>(HttpStatus.OK.value(), "Get chapter successfully", chapterService.getAllChapter());
        }catch (Exception e){
            log.error("Error get all chapter ", e);
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Error get chapter");
        }
    }

    @GetMapping(EndPoint.Chapter.ID)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<ChapterResponse> getChapter(@PathVariable Long chapterId){
        log.info("get chapter by id {}", chapterId);
        try{
            return new ResponseData<>(HttpStatus.OK.value(), "", chapterService.getChapterById(chapterId));
        }catch (Exception e){
            log.info("Error get chapter ", e);
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "");
        }
    }

    @PutMapping(EndPoint.Chapter.ID)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<ChapterResponse> updateChapter(@PathVariable Long id, @RequestBody @Valid ChapterRequestDTO requestDTO){
        log.info("update chapter by id {}", id);
        try{
            return new ResponseData<>(HttpStatus.OK.value(), "", chapterService.updateChapter(id, requestDTO));
        }catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "");
        }
    }

    @DeleteMapping(EndPoint.Chapter.ID)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<Void> deleteChapter(@PathVariable Long id){
        log.info("delete chapter by id {}", id);
        try{
            chapterService.deleteChapter(id);
            return new ResponseData<>(HttpStatus.OK.value(), "");
        }catch (Exception e){
            return new ResponseError(HttpStatus.BAD_REQUEST.value(),"");
        }
    }

}
