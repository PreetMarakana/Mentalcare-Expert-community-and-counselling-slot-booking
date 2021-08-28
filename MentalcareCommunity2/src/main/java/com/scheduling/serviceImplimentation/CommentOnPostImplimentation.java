package com.scheduling.serviceImplimentation;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.scheduling.dto.CommentOnPostDto;
import com.scheduling.dto.CommentOnPostResponseDto;
import com.scheduling.dto.CommentResponseDto;
import com.scheduling.dto.UserResponseDto;
import com.scheduling.model.CommentOnPost;
import com.scheduling.model.LikePostComment;
import com.scheduling.model.Post;
import com.scheduling.model.UserEntity;
import com.scheduling.notification.PushNotificationRequest;
import com.scheduling.notification.PushNotificationService;
import com.scheduling.repository.CommentOnPostRepository;
import com.scheduling.services.CommentOnPostService;
import com.scheduling.services.NotificationService;
import com.scheduling.services.PostService;
import com.scheduling.services.UserServices;

@Service
public class CommentOnPostImplimentation implements CommentOnPostService {

    @Autowired
    private UserServices userServices;
    
    @Autowired
    private PostService postService;
    
    @Autowired
    private CommentOnPostRepository commentOnPostRepository;
    
    @Autowired
    private PushNotificationService pushNotificationService;
    
    @Autowired
    private NotificationService notificationService;
    
    @Override
    public CommentOnPostResponseDto createcommentOnPost(Long postId, CommentOnPostDto commentOnPostDto,
            String username) {
     
        try {
            UserEntity userEntity = userServices.getUserByEmail(username);
            Post post = postService.getPostById(postId);
            
            if (post!=null && userEntity!=null) {
                CommentOnPost commentOnPost = new CommentOnPost();
                commentOnPost.setComment(commentOnPostDto.getComment());
                commentOnPost.setCommnentDate(Date.valueOf(LocalDate.now()));
                commentOnPost.setCommentTime(Time.valueOf(LocalTime.now()));
                commentOnPost.setUserEntity(userEntity);
                commentOnPost.setPost(post);
                CommentOnPost saved = commentOnPostRepository.save(commentOnPost);
                if (sendNotification(userEntity.getUserName(),post.getUserEntity().getMobileToken())) {
                    notificationService.createNotification(post.getUserEntity(),userEntity,"E-dost ",userEntity.getUserName() +" comment on your post",Date.valueOf(LocalDate.now()),Time.valueOf(LocalTime.now()));
                };
                return convertor(saved);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    
    private boolean sendNotification(String username,String token) {
        if (token!=null) {
            try {
                PushNotificationRequest notificationRequest = new PushNotificationRequest("E-dost ",username +" comment on your post",token);
                pushNotificationService.sendPushNotificationToToken(notificationRequest);
            } catch (Exception e) {
                e.printStackTrace();
            }
       }  
       return true; 
   }

    private CommentOnPostResponseDto convertor(CommentOnPost saved) {
        CommentOnPostResponseDto commentOnPostResponseDto = new CommentOnPostResponseDto();
        commentOnPostResponseDto.setCommentDate(saved.getCommnentDate());
        commentOnPostResponseDto.setCommentTime(saved.getCommentTime());
        commentOnPostResponseDto.setUserResponseDto(new UserResponseDto(saved.getUserEntity().getUserId(), saved.getUserEntity().getUserName(), saved.getUserEntity().getProfilePath()));
        commentOnPostResponseDto.setCommentOnPostDto(new CommentOnPostDto(saved.getComment()));
        commentOnPostResponseDto.setPostId(saved.getPost().getPostId());
        commentOnPostResponseDto.setCommentId(saved.getCommentOnPostId());
        return commentOnPostResponseDto;
    }

    @Override
    public boolean deletecommentOnPost(Long commentId, String username) {
        
        try {
            UserEntity userEntity = userServices.getUserByEmail(username);
            
            Optional<CommentOnPost> commentOnPost = commentOnPostRepository.findById(commentId);
            
            if (commentOnPost.isPresent()) {
                if (userEntity!=null) {
                    if (commentOnPost.get().getUserEntity().equals(userEntity)) {
                        commentOnPostRepository.delete(commentOnPost.get());
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public CommentOnPost getCommentByCommentIdOnPost(Long commentId) {
        Optional<CommentOnPost> commentOnPost = commentOnPostRepository.findById(commentId);
        if (commentOnPost.isPresent()) {
            return commentOnPost.get();
        }
        return null;
    }

    @Override
    public List<CommentResponseDto> getAllCommentsOfPost(Long postId, UserDetails details) {
        try {
            Post post = postService.getPostById(postId);
            if (post!=null) {
                if (details!=null) {
                    UserEntity userEntity = userServices.getUserByEmail(details.getUsername());
                    List<CommentResponseDto> commentResponseDtos = convertorToDto(commentOnPostRepository.getAllCommentsByPost(post),userEntity);
                    return commentResponseDtos;
                }else {
                    List<CommentResponseDto> commentResponseDtos = convertorToDto(commentOnPostRepository.getAllCommentsByPost(post),null);
                    return commentResponseDtos;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private List<CommentResponseDto> convertorToDto(List<CommentOnPost> allCommentsByPost,UserEntity userEntity) {
        
        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
        
        if (userEntity!=null) {
            if (allCommentsByPost!=null) {
                allCommentsByPost.stream().forEach(comments ->{
                    CommentResponseDto commentResponseDto = new CommentResponseDto();
                    commentResponseDto.setCommentId(comments.getCommentOnPostId());
                    commentResponseDto.setComment(comments.getComment());
                    commentResponseDto.setNoComments(Long.valueOf(comments.getCommentReplies().size()));
                    commentResponseDto.setNoLikes(Long.valueOf(comments.getLikePostComments().size()));
                    commentResponseDto.setPostDate(comments.getCommnentDate());
                    commentResponseDto.setPostTime(comments.getCommentTime());
                    commentResponseDto.setLiked(userLikedOrNot(comments.getLikePostComments(),userEntity));
                    commentResponseDto.setUserResponseDto(userDtoConvertor(comments.getUserEntity()));
                    commentResponseDtos.add(commentResponseDto);
                });
            }
        }else {
            if (allCommentsByPost!=null) {
                allCommentsByPost.stream().forEach(comments ->{
                    CommentResponseDto commentResponseDto = new CommentResponseDto();
                    commentResponseDto.setCommentId(comments.getCommentOnPostId());
                    commentResponseDto.setComment(comments.getComment());
                    commentResponseDto.setNoComments(Long.valueOf(comments.getCommentReplies().size()));
                    commentResponseDto.setNoLikes(Long.valueOf(comments.getLikePostComments().size()));
                    commentResponseDto.setPostDate(comments.getCommnentDate());
                    commentResponseDto.setPostTime(comments.getCommentTime());
                    commentResponseDto.setLiked(false);
                    commentResponseDto.setUserResponseDto(userDtoConvertor(comments.getUserEntity()));
                    commentResponseDtos.add(commentResponseDto);
                });
            }
        }
        return commentResponseDtos;
    }

    private UserResponseDto userDtoConvertor(UserEntity userEntity) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUsername(userEntity.getUserName());
        userResponseDto.setProfilePath(userEntity.getProfilePath());
        userResponseDto.setUserId(userEntity.getUserId());
        return userResponseDto;
    }

    private boolean userLikedOrNot(List<LikePostComment> likePostComments, UserEntity userEntity) {
        for (LikePostComment likePostComment : likePostComments) {
            if (likePostComment.getUserEntity().equals(userEntity)) {
                return true;
            }
        }
        return false;
    }

}

