package com.tomzxy.webQuiz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.tomzxy.webQuiz.dto.request.chapter.ChapterRequestDTO;
import com.tomzxy.webQuiz.dto.response.Chapter.ChapterResponse;
import com.tomzxy.webQuiz.model.Chapter;

@Mapper(componentModel = "spring")
public interface ChapterMapper {
    ChapterResponse toChapterResponse(Chapter chapter);

    Chapter toChapter(ChapterRequestDTO chapterRequest);

    void updateChapter(@MappingTarget Chapter chapter, ChapterRequestDTO chapterRequest);
}
