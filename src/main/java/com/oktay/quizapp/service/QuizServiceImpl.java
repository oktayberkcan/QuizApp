package com.oktay.quizapp.service;

import com.oktay.quizapp.dao.QuestionRepository;
import com.oktay.quizapp.dao.QuizRepository;
import com.oktay.quizapp.dto.requests.CreateQuizRequest;
import com.oktay.quizapp.dto.responses.FindRandomQuestionsByCategory;
import com.oktay.quizapp.dto.responses.GetQuizQuestions;
import com.oktay.quizapp.mapper.ModelMapperService;
import com.oktay.quizapp.model.entities.Question;
import com.oktay.quizapp.model.entities.Quiz;
import com.oktay.quizapp.model.entities.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class QuizServiceImpl implements QuizService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;
    private final ModelMapperService modelMapperService;

    @Override
    public void create(CreateQuizRequest createQuizRequest, String category, int numQ) {

        List<Question> questions = this.questionRepository.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = Quiz.builder()
                .quizTitle(createQuizRequest.getQuizTitle())
                .questions(questions)
                .build();

        this.quizRepository.save(quiz);
        log.info("Quiz {} created", quiz.getId());

    }

    @Override
    public List<FindRandomQuestionsByCategory> findRandomQuestionsByCategory(String category, int numQ) {

        List<Question> questions = questionRepository.findRandomQuestionsByCategory(category, numQ);

        return questions.stream()
                .map(question -> this.modelMapperService.forResponse()
                        .map(question, FindRandomQuestionsByCategory.class)).collect(Collectors.toList());


    }

    @Override
    public List<GetQuizQuestions> getQuizQuestions(int id) {

        Optional<Quiz> quizList =  quizRepository.findById(id);
        List<Question> questionsFromDB = quizList.get().getQuestions();
        List<GetQuizQuestions> questionsForUser = new ArrayList<>();

        for (Question q : questionsFromDB) {
            GetQuizQuestions qFU = new GetQuizQuestions(
            q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qFU);
        }

            return questionsForUser;

    }


    @Override
    public void calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Question> questions = quiz.getQuestions();

        int right = 0;
        int i = 0;
        for (Response response : responses) {
            if (response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;

            i++;
        }

    }
}
