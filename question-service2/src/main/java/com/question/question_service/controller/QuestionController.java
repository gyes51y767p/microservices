package com.question.question_service.controller;



import com.question.question_service.dto.Question;
import com.question.question_service.dto.Response;
import com.question.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService ;


   @Autowired
    Environment environment;

    @GetMapping("getAllQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);

    }

    @PostMapping("addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @DeleteMapping("deleteQuestion/{id}")
    public void deleteQuestion(@PathVariable Integer id){
        questionService.deleteQuestion(id);
    }

    //getScrore
    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }
    //generateQuestions

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,@RequestParam Integer numQ){
        return questionService.getQuestionsForQuiz(categoryName,numQ);
    }
    //getQuestions(questionId)

    @PostMapping("getQuestions")
    public ResponseEntity<List<Question>> getQuestionFromID(@RequestBody List<Integer> questionIds){
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromId(questionIds);
    }
}
