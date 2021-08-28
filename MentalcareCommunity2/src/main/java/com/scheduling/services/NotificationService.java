package com.scheduling.services;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.stereotype.Service;

import com.scheduling.dto.NotificationResponseDto;
import com.scheduling.model.UserEntity;

@Service
public interface NotificationService {

    void createNotification(UserEntity receiver, UserEntity sender, String title, String message, Date date,
            Time time);

    List<NotificationResponseDto> getUserNotification(String username);

}
