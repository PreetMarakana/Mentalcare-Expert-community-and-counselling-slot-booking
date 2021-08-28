package com.scheduling.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scheduling.model.Post;
import com.scheduling.model.PostLike;
import com.scheduling.model.UserEntity;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike,Long> {

    @Query("select l from PostLike l where l.post=?1 AND l.userEntity=?2")
    Optional<PostLike> getPostLike(Post post, UserEntity userEntity);

}
