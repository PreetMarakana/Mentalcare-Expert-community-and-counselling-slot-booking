package com.scheduling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scheduling.model.CommentOnAnswer;
import com.scheduling.model.LikeAnswerComment;
import com.scheduling.model.UserEntity;

@Repository
public interface LikeAnswerCommentRepository extends JpaRepository<LikeAnswerComment, Long> {

    @Query(value = "select lc from LikeAnswerComment as lc where lc.commentOnAnswer=?1 AND lc.userEntity=?2")
    LikeAnswerComment getlikeAnswerComment(CommentOnAnswer commentOnAnswer, UserEntity userEntity);

}
