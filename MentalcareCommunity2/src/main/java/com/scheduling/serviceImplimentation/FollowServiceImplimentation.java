package com.scheduling.serviceImplimentation;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scheduling.dto.AnswerResponseDto;
import com.scheduling.model.Follow;
import com.scheduling.model.UserEntity;
import com.scheduling.notification.PushNotificationRequest;
import com.scheduling.notification.PushNotificationService;
import com.scheduling.repository.FollowServiceRepository;
import com.scheduling.services.FollowService;
import com.scheduling.services.NotificationService;
import com.scheduling.services.UserServices;

@Service
public class FollowServiceImplimentation implements FollowService {

    @Autowired
    private FollowServiceRepository followServiceRepository;

    @Autowired
    private UserServices userServices;
    
    @Autowired
    private PushNotificationService pushNotificationService;
    
    @Autowired
    private NotificationService notificationService;
    

    @Override
    public String followUser(Long userId, String username) {
        try {
            UserEntity following = userServices.getUserByUserId(userId);
            UserEntity follower = userServices.getUserByEmail(username);

            if (following != null && follower != null) {

                Optional<Follow> follow = followServiceRepository.getFollow(following, follower);
                if (follow.isPresent()) {
                    followServiceRepository.delete(follow.get());
                    return "unFollow";
                } else {
                    Follow follow2 = new Follow();
                    follow2.setFromUser(follower);
                    follow2.setToUser(following);
                    follow2.setFollowDate(Date.valueOf(LocalDate.now()));
                    follow2.setFollowTime(Time.valueOf(LocalTime.now()));
                    followServiceRepository.save(follow2);
                    if (sendNotification(follower.getUserName(),following.getMobileToken())) {
                        notificationService.createNotification(following,follower,"E-dost ",follower.getUserName() +" started following you",Date.valueOf(LocalDate.now()),Time.valueOf(LocalTime.now()));
                    }
                    return "Follow";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "error";
    }
    
    private boolean sendNotification(String username,String token) {
        if (token!=null) {
            try {
                PushNotificationRequest notificationRequest = new PushNotificationRequest("E-dost ",username +" started following you",token);
                pushNotificationService.sendPushNotificationToToken(notificationRequest);
                return true;
            } catch (Exception e) {
               e.printStackTrace();
           }
        } 
        return true;
   }
    

}













