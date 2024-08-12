package com.question.question_service.service;


import com.question.question_service.dao.QuestionDao;
import com.question.question_service.dto.Question;
import com.question.question_service.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ArrayList<>());
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try{
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ArrayList<>());
    }

    public ResponseEntity<String> addQuestion(Question question) {


        try{
            questionDao.save(question);
            return new ResponseEntity<>("success",HttpStatus.CREATED);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("failed",HttpStatus.BAD_REQUEST);

    }

    public void deleteQuestion(Integer id) {
        questionDao.deleteById(id);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQ) {
        List<Integer> questions=questionDao.findRandomQuestionsByCategory(categoryName,numQ);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<List<Question>> getQuestionsFromId(List<Integer> questionIds) {
        List<Question> questions=new ArrayList<>();
        for (Integer id:questionIds){
            questions.add(questionDao.findById(id).get());
        }
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int right=0;

        for(Response response:responses){
          Question question=questionDao.findById(response.getId()).get();
            if(response.getResponse().equals(question.getCorrect_answer()))
                right++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
