package com.question.question_service.dao;

import com.question.question_service.dto.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>{
    List<Question> findByCategory(String category);

    @Query(value = "select q.id from question q where q.category=:category order by Random() limit :numQ" , nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int numQ);
}
