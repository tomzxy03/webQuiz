package com.tomzxy.webQuiz;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomzxy.webQuiz.dto.request.question.QuestionCreateRequestDTO;

public class Test {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        String json = "{ \"questionText\": \"Java là gì?\", \"level\": \"easy\", \"answers\": [{ \"answer_text\": \"Là ngôn ngữ lập trình\", \"correct_answer\": true }] }";
        QuestionCreateRequestDTO dto = mapper.readValue(json, QuestionCreateRequestDTO.class);

        System.out.println(dto.getQuestionText());
        System.out.println(dto.getLevel());
        System.out.println(dto.getAnswers());
    }
}
