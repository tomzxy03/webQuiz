package com.tomzxy.webQuiz.mapper;

import com.tomzxy.webQuiz.constants.EndPoint;
import com.tomzxy.webQuiz.dto.request.chapter.ChapterRequestDTO;
import com.tomzxy.webQuiz.dto.request.subject.SubjectRequest;
import com.tomzxy.webQuiz.dto.request.subject.SubjectUpdateRequest;
import com.tomzxy.webQuiz.dto.response.Chapter.ChapterResponse;
import com.tomzxy.webQuiz.dto.response.Subject.SubjectResponse;
import com.tomzxy.webQuiz.model.Chapter;
import com.tomzxy.webQuiz.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ChapterMapper {
    ChapterResponse toChapterResponse(Chapter chapter);

    Chapter toChapter(ChapterRequestDTO chapterRequest);

    void updateChapter(@MappingTarget Chapter chapter, ChapterRequestDTO chapterRequest);
}
