package com.scheduling.serviceImplimentation;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scheduling.model.Answer;
import com.scheduling.model.LikeAnswer;
import com.scheduling.model.UserEntity;
import com.scheduling.notification.PushNotificationRequest;
import com.scheduling.notification.PushNotificationService;
import com.scheduling.repository.LikeAnswerRepository;
import com.scheduling.services.AnswerService;
import com.scheduling.services.LikeAnswerService;
import com.scheduling.services.NotificationService;
import com.scheduling.services.UserServices;

@Service
public class LikeAnswerImplimentation implements LikeAnswerService {

    @Autowired
    private AnswerService answerService;
    
    @Autowired
    private UserServices userServices;
    
    @Autowired
    private LikeAnswerRepository likeAnswerRepo;
    
    @Autowired
    private PushNotificationService pushNotificationService;
    
    @Autowired
    private NotificationService notificationService;
    
    @Override
    public String likeAnswer(Long answerId, String username) {
        try {
            Answer answer = answerService.getanswerByAnswerId(answerId);
            UserEntity userEntity = userServices.getUserByEmail(username);
            
            if (answer!=null && userEntity!=null) {
                Optional<LikeAnswer> likeAnswer = likeAnswerRepo.likedOrNot(answer,userEntity);
                if (likeAnswer.isPresent()) {
                    likeAnswerRepo.delete(likeAnswer.get());
                    return "dislike";
                }else {
                    LikeAnswer likeAnswer2 = new LikeAnswer();
                    likeAnswer2.setAnswer(answer);
                    likeAnswer2.setUserEntity(userEntity);
                    likeAnswer2.setLikeDate(Date.valueOf(LocalDate.now()));
                    likeAnswer2.setLikeTime(Time.valueOf(LocalTime.now()));
                    likeAnswerRepo.save(likeAnswer2);
                    if (sendNotification(userEntity.getUserName(),answer.getUserEntity().getMobileToken())) {
                        notificationService.createNotification(answer.getUserEntity(), userEntity, "E-dost", userEntity.getUserName() +" likes your answer", Date.valueOf(LocalDate.now()),Time.valueOf(LocalTime.now()));
                    };
                    return "liked";
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
                PushNotificationRequest notificationRequest = new PushNotificationRequest("E-dost",username +" likes your answer",token);
                pushNotificationService.sendPushNotificationToToken(notificationRequest);
                return true;
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
       }   
       return true;
   }
    

}
