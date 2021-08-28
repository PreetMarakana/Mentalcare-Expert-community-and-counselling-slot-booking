package com.scheduling.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scheduling.model.Follow;
import com.scheduling.model.UserEntity;

@Repository
public interface FollowServiceRepository extends JpaRepository<Follow, Long> {

    @Query(value = "select f from Follow f where f.fromUser=?2 AND f.toUser=?1")
    Optional<Follow> getFollow(UserEntity following, UserEntity follower);

}
