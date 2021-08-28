package com.scheduling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scheduling.model.CommentOnPost;
import com.scheduling.model.PostCommentReply;

@Repository
public interface CommentOnPostReplieRepository extends JpaRepository<PostCommentReply,Long> {

    @Query("select pr from PostCommentReply as pr where pr.CommentOnPost=?1")
    List<PostCommentReply> getCommentsReplies(CommentOnPost commentOnPost);

}
