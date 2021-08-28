package com.scheduling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scheduling.model.CommentOnPost;
import com.scheduling.model.Post;

public interface CommentOnPostRepository extends JpaRepository<CommentOnPost,Long> {

    @Query("select cp from CommentOnPost as cp where cp.post=?1")
    List<CommentOnPost> getAllCommentsByPost(Post post);

}
