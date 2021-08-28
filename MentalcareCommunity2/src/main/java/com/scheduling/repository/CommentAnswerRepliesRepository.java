package com.scheduling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scheduling.model.AnswerCommentReply;
import com.scheduling.model.CommentOnAnswer;

@Repository
public interface CommentAnswerRepliesRepository extends JpaRepository<AnswerCommentReply,Long> {

    @Query(value = "select ar from AnswerCommentReply as ar where ar.commentOnAnswer=?1")
    List<AnswerCommentReply> getCommentsReplies(CommentOnAnswer commentOnAnswer);

}
