package com.scheduling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scheduling.model.Answer;
import com.scheduling.model.CommentOnAnswer;

@Repository
public interface CommetOnAnswerRepository extends JpaRepository<CommentOnAnswer,Long> {

    @Query(value = "select cs from CommentOnAnswer as cs where cs.answer=?1")
    List<CommentOnAnswer> getAllCommentsOfAnswer(Answer answer);

}
