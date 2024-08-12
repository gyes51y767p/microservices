package com.quiz.quiz_service.feign;

import com.quiz.quiz_service.dto.Question;
import com.quiz.quiz_service.dto.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
//@Component
public interface QuizInterface {
    //getScrore
    @PostMapping("questions/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
    //generateQuestions

    @GetMapping("questions/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numQ);
    //getQuestions(questionId)

    @PostMapping("questions/getQuestions")
    public ResponseEntity<List<Question>> getQuestionFromID(@RequestBody List<Integer> questionIds);
}
