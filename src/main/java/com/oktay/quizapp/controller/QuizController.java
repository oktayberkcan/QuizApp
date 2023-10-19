package com.oktay.quizapp.controller;

import com.oktay.quizapp.dto.requests.CreateQuizRequest;
import com.oktay.quizapp.dto.responses.FindRandomQuestionsByCategory;
import com.oktay.quizapp.dto.responses.GetQuizQuestions;
import com.oktay.quizapp.model.entities.Response;
import com.oktay.quizapp.service.QuizServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

    QuizServiceImpl quizService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(CreateQuizRequest createQuizRequest, String category, int numQ) {
        this.quizService.create(createQuizRequest, category, numQ);
    }
    @GetMapping("randomQuestions/{category}/{numQ}")
    @ResponseStatus(HttpStatus.OK)
    public List<FindRandomQuestionsByCategory> findRandomQuestionsByCategories(@PathVariable String category, @PathVariable int numQ) {
        return this.quizService.findRandomQuestionsByCategory(category, numQ);
    }
    @GetMapping("getQuizQuestions/{id}")
    public List<GetQuizQuestions> getQuizQuestions(@PathVariable int id) {
        return this.quizService.getQuizQuestions(id);

    }
    @PostMapping("submit/{id}")
    public void submitQuiz( @PathVariable Integer id, @RequestBody List<Response> responses) {
        this.quizService.calculateResult(id, responses);
    }
}
