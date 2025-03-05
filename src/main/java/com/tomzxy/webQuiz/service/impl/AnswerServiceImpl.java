package com.tomzxy.webQuiz.service.impl;


import com.tomzxy.webQuiz.dto.request.answer.AnswerRequestDTO;
import com.tomzxy.webQuiz.dto.response.Answer.AnswerResponse;
import com.tomzxy.webQuiz.exception.ResourceNotFoundException;
import com.tomzxy.webQuiz.mapper.AnswerMapper;
import com.tomzxy.webQuiz.model.Answer;
import com.tomzxy.webQuiz.repository.AnswerRepository;
import com.tomzxy.webQuiz.service.AnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;

    @Override
    public List<AnswerResponse> getAllAnswer() {
        return answerRepository.findAll().stream().map(answerMapper::toAnswerResponse).toList();
    }

    @Override
    public AnswerResponse getAnswerById(Long answerId) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(()-> new ResourceNotFoundException("Answer not found"));
        return answerMapper.toAnswerResponse(answer);
    }

    @Override
    public AnswerResponse updateAnswer(Long answerId, AnswerRequestDTO answerRequestDTO) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(()-> new ResourceNotFoundException("Answer not found"));

        answerMapper.updateAnswer(answer,answerRequestDTO);
        return answerMapper.toAnswerResponse(answerRepository.save(answer));
    }

    @Override
    public void deleteAnswer(Long answerId) {
        answerRepository.deleteById(answerId);
    }
}
