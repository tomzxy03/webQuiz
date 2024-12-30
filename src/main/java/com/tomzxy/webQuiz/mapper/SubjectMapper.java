package com.tomzxy.webQuiz.mapper;

import com.tomzxy.webQuiz.dto.request.subject.SubjectRequest;
import com.tomzxy.webQuiz.dto.request.subject.SubjectUpdateRequest;
import com.tomzxy.webQuiz.dto.response.Subject.SubjectResponse;
import com.tomzxy.webQuiz.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    SubjectResponse toSubjectResponse(Subject subject);

    Subject toSubject(SubjectRequest subjectRequest);

    @Mapping(target = "chapters", ignore = true)
    void updateSubject(@MappingTarget Subject subject, SubjectUpdateRequest subjectRequest);
}
