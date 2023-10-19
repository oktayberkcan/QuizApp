package com.oktay.quizapp.service;

import com.oktay.quizapp.dto.requests.CreateQuizRequest;
import com.oktay.quizapp.dto.responses.FindRandomQuestionsByCategory;
import com.oktay.quizapp.dto.responses.GetQuizQuestions;
import com.oktay.quizapp.model.entities.Response;

import java.util.List;

public interface QuizService {

    void create(CreateQuizRequest createQuizRequest, String category, int numQ);
    List<FindRandomQuestionsByCategory> findRandomQuestionsByCategory(String category, int numQ);
    List<GetQuizQuestions> getQuizQuestions(int id);

    void calculateResult(Integer id, List<Response> responses);
}
