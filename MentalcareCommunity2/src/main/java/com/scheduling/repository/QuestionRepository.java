package com.scheduling.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scheduling.model.Question;
import com.scheduling.model.UserEntity;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

    @Query(value = "select q from Question q ",countQuery = "select count(q) from Question q ")
    Page<Question> getFeedQuestions(Pageable pageable);

    @Query(value = "select q from Question as q where q.userEntity=?1",countQuery = "select count(q) from Question as q")
    Page<Question> getUserQuestion(UserEntity profileUser, Pageable pageable);

    
}
