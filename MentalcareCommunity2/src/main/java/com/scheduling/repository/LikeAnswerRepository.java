package com.scheduling.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scheduling.model.Answer;
import com.scheduling.model.LikeAnswer;
import com.scheduling.model.UserEntity;

@Repository
public interface LikeAnswerRepository  extends JpaRepository<LikeAnswer,Long> {

    @Query("select l from LikeAnswer l where l.answer=?1 AND l.userEntity=?2")
    Optional<LikeAnswer> likedOrNot(Answer answer, UserEntity userEntity);

}
