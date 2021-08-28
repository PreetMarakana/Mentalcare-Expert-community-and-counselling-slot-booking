package com.scheduling.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scheduling.model.Answer;
import com.scheduling.model.UserEntity;

@Repository
public interface AnswerRepository extends JpaRepository<Answer , Long>  {

    @Query(value = "select a from Answer a",countQuery = "select count(a) from Answer a")
    Page<Answer> getAnswerFeeds(Pageable pageable);

    @Query(value = "select a from Answer as a where a.userEntity=?1",countQuery = "select count(a) from Answer as a where a.userEntity=?1")
    Page<Answer> getUserAnswers(UserEntity profileUser, Pageable pageable);

}
