package com.oktay.quizapp.service;

import com.oktay.quizapp.dto.requests.CreateQuestionRequest;
import com.oktay.quizapp.dto.requests.UpdateQuestionRequest;
import com.oktay.quizapp.dto.responses.GetAllQuestionsResponse;
import com.oktay.quizapp.dto.responses.GetQuestionsByCategory;

import java.util.List;

public interface QuestionService {
    List<GetAllQuestionsResponse> getAllQuestions();
    List<GetQuestionsByCategory> getQuestionsByCategory(String category);

    void add(CreateQuestionRequest createQuestionRequest);
    void update(UpdateQuestionRequest updateQuestionRequest);
    void delete (int id);
}
