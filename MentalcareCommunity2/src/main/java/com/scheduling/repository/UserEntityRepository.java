package com.scheduling.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scheduling.model.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity,Long> {

    @Query(value = "from UserEntity where userEmail=?1")
    Optional<UserEntity> findByuserEmailAnduserVerify(String username);

    @Query(value = "from UserEntity where faceBookId=?1")
    UserEntity getUserByFacebookId(String id);

//    Optional<UserEntity> findByuserEmailAndIsuserVerify(String userEmail, boolean b);

}
