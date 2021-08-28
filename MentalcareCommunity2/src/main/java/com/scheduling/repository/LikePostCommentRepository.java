package com.scheduling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scheduling.model.CommentOnPost;
import com.scheduling.model.LikePostComment;
import com.scheduling.model.UserEntity;

@Repository
public interface LikePostCommentRepository extends JpaRepository<LikePostComment,Long> {

    @Query(value = "select lc from LikePostComment as lc where lc.userEntity=?1 AND lc.commentOnPost=?2")
    LikePostComment getLikePostComment(UserEntity entity, CommentOnPost commentPost);

}
