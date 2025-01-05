package com.tomzxy.webQuiz.service.impl;

import com.tomzxy.webQuiz.dto.request.answer.AnswerRequestDTO;
import com.tomzxy.webQuiz.dto.request.question.QuestionCreateRequestDTO;
import com.tomzxy.webQuiz.dto.request.question.QuestionUpdateRequestDTO;
import com.tomzxy.webQuiz.dto.response.Question.QuestionResponse;
import com.tomzxy.webQuiz.exception.ResourceNotFoundException;
import com.tomzxy.webQuiz.mapper.QuestionMapper;
import com.tomzxy.webQuiz.model.Answer;
import com.tomzxy.webQuiz.model.Question;
import com.tomzxy.webQuiz.repository.AnswerRepository;
import com.tomzxy.webQuiz.repository.QuestionRepository;
import com.tomzxy.webQuiz.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final AnswerRepository answerRepository;


    @Override
    @Transactional
    public QuestionResponse addQuestion(QuestionCreateRequestDTO requestDTO) {
        Question question = questionMapper.toQuestion(requestDTO);
        question.getAnswers().forEach(a-> System.out.println("ansT"+a.getAnswerText()));
        if(question.getAnswers()!=null){
            question.getAnswers().forEach(question::saveAnswers);
        }
        return questionMapper.toQuestionResponse(questionRepository.save(question));

    }

    @Override
    public List<QuestionResponse> getAllQuestion() {
        return questionRepository.findAll().stream().map(questionMapper::toQuestionResponse).toList();
    }

    @Override
    public QuestionResponse getQuestionById(Long questionId) {
        Question question = findById(questionId);
        return questionMapper.toQuestionResponse(question);
    }

    @Override
    public QuestionResponse updateQuestion(Long questionId, QuestionUpdateRequestDTO questionUpdateRequestDTO) {
        Question question=findById(questionId);

        questionMapper.updateQuestion(question,questionUpdateRequestDTO);

        if(questionUpdateRequestDTO.getAnswers()!= null){
            var answers = answerRepository.findAllByAnswerText(questionUpdateRequestDTO.getAnswers());
            question.setAnswers((Set<Answer>) answers);
        }
        question.getAnswers().forEach(a-> System.out.println("a: "+a.getAnswerText()));
        return questionMapper.toQuestionResponse(questionRepository.save(question));
    }

    @Override
    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    public Question findById(Long id){
        return questionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Question not found!!"));
    }


    protected Set<Answer> ConvertAnswerDTOtoAnswer(Set<AnswerRequestDTO> answerRequestDTO){
        Set<Answer> answers = new HashSet<>();
        answerRequestDTO.forEach(a->
                answers.add(Answer.builder()
                                .correct_answer(a.getCorrect_answer())
                                .answerText(a.getAnswer_text())
                            .build())
                );
        return answers;
    }
}
