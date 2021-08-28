package com.scheduling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scheduling.model.Notification;
import com.scheduling.model.UserEntity;

@Repository
public interface NotificationRepository extends JpaRepository<Notification , Long> {

    @Query("from Notification where receiver=?1")
    List<Notification> findAllByUser(UserEntity entity);
 
}
