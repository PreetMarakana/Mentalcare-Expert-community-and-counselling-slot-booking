package com.scheduling.serviceImplimentation;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scheduling.model.Post;
import com.scheduling.model.PostLike;
import com.scheduling.model.UserEntity;
import com.scheduling.notification.PushNotificationRequest;
import com.scheduling.notification.PushNotificationService;
import com.scheduling.repository.PostLikeRepository;
import com.scheduling.services.NotificationService;
import com.scheduling.services.PostLikeService;
import com.scheduling.services.PostService;
import com.scheduling.services.UserServices;

@Service
public class PostLikeImplimentation implements PostLikeService {

    @Autowired
    private PostLikeRepository postLikeRepository;
    
    @Autowired
    private PostService postService;
    
    @Autowired
    private UserServices userServices;
    
    @Autowired
    private PushNotificationService pushNotificationService;
    
    @Autowired
    private NotificationService notificationService;
    
    @Override
    public String postLike(Long postId, String username) {
        
        try {
            UserEntity userEntity = userServices.getUserByEmail(username);
            
            Post post = postService.getPostById(postId);
            
            if (post!=null && userEntity!=null) {
                Optional<PostLike> postLike = postLikeRepository.getPostLike(post,userEntity);
                if (postLike.isPresent()) {
                    postLikeRepository.delete(postLike.get());
                    return "disliked";
                }else {
                    PostLike postLike2 = new PostLike();
                    postLike2.setPost(post);
                    postLike2.setUserEntity(userEntity);
                    postLike2.setLikeDate(Date.valueOf(LocalDate.now()));
                    postLike2.setLikeTime(Time.valueOf(LocalTime.now()));
                    postLikeRepository.save(postLike2);
                    if (sendNotification(userEntity.getUserName(),post.getUserEntity().getMobileToken())) {
                        notificationService.createNotification(post.getUserEntity(),userEntity,"E-dost ",userEntity.getUserName() +" likes your post",Date.valueOf(LocalDate.now()),Time.valueOf(LocalTime.now()));
                    };
                    return "liked";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "error occure";
    }
    
    private boolean sendNotification(String username,String token) {
        if (token!=null) {  
            try {
                PushNotificationRequest notificationRequest = new PushNotificationRequest("E-dost ",username +" likes your post",token);
                pushNotificationService.sendPushNotificationToToken(notificationRequest);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
       }
        return true;
   }
    
}
