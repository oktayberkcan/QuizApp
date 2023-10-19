package com.oktay.quizapp.service;

import com.oktay.quizapp.dao.QuestionRepository;
import com.oktay.quizapp.dto.requests.CreateQuestionRequest;
import com.oktay.quizapp.dto.requests.UpdateQuestionRequest;
import com.oktay.quizapp.dto.responses.GetAllQuestionsResponse;
import com.oktay.quizapp.dto.responses.GetQuestionsByCategory;
import com.oktay.quizapp.mapper.ModelMapperService;
import com.oktay.quizapp.model.entities.Question;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    QuestionRepository questionRepository;
    ModelMapperService modelMapperService;

    @Override
    public List<GetAllQuestionsResponse> getAllQuestions() {
            List<Question> questions = questionRepository.findAll();

            return questions.stream()
                    .map(question -> this.modelMapperService.forResponse()
                            .map(question, GetAllQuestionsResponse.class)).collect(Collectors.toList());

        }

    @Override
    public List<GetQuestionsByCategory> getQuestionsByCategory(String category) {
        List<Question> questions = questionRepository.findByCategory(category);

        return questions.stream()
                .map(question -> this.modelMapperService.forResponse()
                        .map(question, GetQuestionsByCategory.class)).collect(Collectors.toList());

    }

    @Override
    public void add(CreateQuestionRequest createQuestionRequest) {

        Question question = this.modelMapperService.forRequest()
                .map(createQuestionRequest, Question.class);

        this.questionRepository.save(question);
        log.info("Question {} saved", question.getId());
    }

    @Override
    public void update(UpdateQuestionRequest updateQuestionRequest) {
        Question question = this.modelMapperService.forRequest()
                .map(updateQuestionRequest, Question.class);

        this.questionRepository.save(question);
        log.info("Question {} updated", question.getId());
    }

    @Override
    public void delete(int id) {
        this.questionRepository.deleteById(id);
        log.info("Question {} deleted");
    }
}
