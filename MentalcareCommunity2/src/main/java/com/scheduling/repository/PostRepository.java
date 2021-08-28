package com.scheduling.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.scheduling.model.Post;
import com.scheduling.model.UserEntity;

@Service
public interface PostRepository extends JpaRepository<Post,Long> {

    @Query(value = "select p from Post p",countQuery = "select count(p) from Post p")
    Page<Post> getFeedPosts(Pageable pageable);

    @Query(value = "select p from Post p where p.userEntity=?1",countQuery = "select count(p) from Post p where p.userEntity=?1")
    Page<Post> getUserPosts(UserEntity profileUser, Pageable pageable);

}
