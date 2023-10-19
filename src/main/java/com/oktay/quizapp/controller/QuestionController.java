package com.oktay.quizapp.controller;

import com.oktay.quizapp.dto.requests.CreateQuestionRequest;
import com.oktay.quizapp.dto.requests.UpdateQuestionRequest;
import com.oktay.quizapp.dto.responses.GetAllQuestionsResponse;
import com.oktay.quizapp.dto.responses.GetQuestionsByCategory;
import com.oktay.quizapp.service.QuestionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class QuestionController {

    QuestionServiceImpl questionService;
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/allQuestions")
    public List<GetAllQuestionsResponse> getAllQuestions() {
        return questionService.getAllQuestions();
    }
    @GetMapping("/category/{category}")
    public List<GetQuestionsByCategory> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public void add(@RequestBody CreateQuestionRequest createQuestionRequest) {
        this.questionService.add(createQuestionRequest);

    }

    @PutMapping("/update")
    public void update(@RequestBody UpdateQuestionRequest updateQuestionRequest) {
        this.questionService.update(updateQuestionRequest);
    }
    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable int id) {
        this.questionService.delete(id);
    }

}
