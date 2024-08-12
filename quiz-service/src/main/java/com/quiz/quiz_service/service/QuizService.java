package com.quiz.quiz_service.service;


import com.quiz.quiz_service.dao.QuizDao;
import com.quiz.quiz_service.dto.Question;
import com.quiz.quiz_service.dto.Quiz;
import com.quiz.quiz_service.dto.Response;
import com.quiz.quiz_service.feign.QuizInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Integer> questions= quizInterface.getQuestionsForQuiz(category,numQ).getBody();
        Quiz quiz= new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsIds(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("sucess", HttpStatus.CREATED);
    }

    public ResponseEntity<List<Question>> getQuizQuestions(Integer id) {
//        public ResponseEntity<List<Question>> getQuestionFromID(@RequestBody List<Integer> questionIds);
//
//        List<Question> questions=quizInterface.getQuestionFromID(id).getBody();
//        List<Integer> ids= quizDao.findById(id).get();
       Quiz quiz= quizDao.findById(id).get();
       List<Integer> questionIds= quiz.getQuestionsIds();
        ResponseEntity<List<Question>> questions =quizInterface.getQuestionFromID(questionIds);


        return questions;
    }

    public ResponseEntity<Integer> submitQuiz(Integer id, List<Response> responses) {
        ResponseEntity<Integer> scroe=quizInterface.getScore(responses);
        return scroe;
    }
}
