package com.scheduling.serviceImplimentation;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scheduling.dto.AnswerDto;
import com.scheduling.dto.AnswerResponseDto;
import com.scheduling.dto.QuestionDto;
import com.scheduling.dto.UserResponseDto;
import com.scheduling.model.Answer;
import com.scheduling.model.Follow;
import com.scheduling.model.LikeAnswer;
import com.scheduling.model.Question;
import com.scheduling.model.UserEntity;
import com.scheduling.notification.PushNotificationRequest;
import com.scheduling.notification.PushNotificationService;
import com.scheduling.repository.AnswerRepository;
import com.scheduling.services.AnswerService;
import com.scheduling.services.NotificationService;
import com.scheduling.services.QuestionService;
import com.scheduling.services.UserServices;

@Service
public class AnswerServiceImplimentation implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;
    
    @Autowired
    private UserServices userServices;
    
    @Autowired
    private QuestionService questionService;
    
    @Autowired
    private PushNotificationService pushNotificationService;
    
    @Autowired
    private NotificationService notificationService;
    
    @Override
    public AnswerResponseDto createAnswer(long qid, AnswerDto answerDto, String username) {
        try {
            Question question = questionService.getQuestionById(qid);
            if (question!=null) {
                UserEntity userEntity = userServices.getUserByEmail(username);
                Answer answer = new Answer();
                answer.setAnswerDate(Date.valueOf(LocalDate.now()));
                answer.setAnswerTime(Time.valueOf(LocalTime.now()));
                answer.setQuestion(question);
                answer.setUserEntity(userEntity);
                answer.setUserAnswer(answerDto.getAnswer());
                Answer saved = answerRepository.save(answer);
                AnswerResponseDto answerResponseDto = new AnswerResponseDto();
                answerResponseDto.setAnswerId(saved.getAnswerId());
                answerResponseDto.setAnswerDate(saved.getAnswerDate());
                answerResponseDto.setAnswerTime(saved.getAnswerTime());
                answerResponseDto.setAnswerDto(new AnswerDto(answer.getUserAnswer()));
                answerResponseDto.setQuestionDto(new QuestionDto(question.getQuestionId(),question.getUserQuestion()));
                answerResponseDto.setUserDto(new UserResponseDto(userEntity.getUserId(), userEntity.getUserName(), userEntity.getProfilePath()));
                if (sendNotification(answerResponseDto,question.getUserEntity().getMobileToken())) {
                    notificationService.createNotification(question.getUserEntity(),userEntity,"E-dost Answer for you",answerResponseDto.getUserDto().getUsername() +" gives answer on your question",Date.valueOf(LocalDate.now()),Time.valueOf(LocalTime.now()));
                }
                return answerResponseDto;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean sendNotification(AnswerResponseDto answerResponseDto,String token) {
         if (token!=null) {
             try {
                 PushNotificationRequest notificationRequest = new PushNotificationRequest("E-dost Answer for you",answerResponseDto.getUserDto().getUsername() +" gives answer on your question",token);
                 pushNotificationService.sendPushNotificationToToken(notificationRequest);
                 return true;
             } catch (Exception e) {
                e.printStackTrace();
            }
         } 
         return false;
    }

    @Override
    public boolean deleteAnswer(String username, long aid) {
        try {
            UserEntity userEntity = userServices.getUserByEmail(username);
            Optional<Answer> answer = answerRepository.findById(aid);
            if (answer.isPresent() && userEntity!=null) {
                if (answer.get().getUserEntity().equals(userEntity)) {
                    answerRepository.delete(answer.get());
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Answer getanswerByAnswerId(Long answerId) {
        try {
            Optional<Answer> answer = answerRepository.findById(answerId);
            if (answer.isPresent()) {
                return answer.get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<AnswerResponseDto> getAnswerFeeds(UserEntity userEntity, Integer pageNo) {
        try {
            Set<AnswerResponseDto> answerResponseDtos = new HashSet<>();
            Pageable pageable = PageRequest.of(pageNo-1, 2,Sort.by("answerTime"));
            if (userEntity!=null) {
                List<Answer> content = answerRepository.getAnswerFeeds(pageable).getContent();
                content.stream().forEach(answer -> {
                    AnswerResponseDto answerResponseDto = new AnswerResponseDto();
                    answerResponseDto.setAnswerId(answer.getAnswerId());
                    answerResponseDto.setAnswerDto(new AnswerDto(answer.getUserAnswer()));
                    answerResponseDto.setAnswerDate(answer.getAnswerDate());
                    answerResponseDto.setAnswerTime(answer.getAnswerTime());
                    answerResponseDto.setQuestionDto(new QuestionDto(answer.getQuestion().getQuestionId(), answer.getQuestion().getUserQuestion()));
                    answerResponseDto.setNoLikes(Long.valueOf(answer.getLikeAnswers().size()));
                    answerResponseDto.setNoComments(Long.valueOf(answer.getCommentOnAnswers().size()));
                    answerResponseDto.setUserDto(new UserResponseDto(answer.getUserEntity().getUserId(), answer.getUserEntity().getUserName(), answer.getUserEntity().getProfilePath(),followOrNot(userEntity, answer.getUserEntity())));
                    answerResponseDto.setLiked(likedOrNot(answer.getLikeAnswers(),userEntity));
                    answerResponseDtos.add(answerResponseDto);
                });
            }else {
                List<Answer> content = answerRepository.getAnswerFeeds(pageable).getContent();
                content.stream().forEach(answer -> {
                    AnswerResponseDto answerResponseDto = new AnswerResponseDto();
                    answerResponseDto.setAnswerId(answer.getAnswerId());
                    answerResponseDto.setAnswerDto(new AnswerDto(answer.getUserAnswer()));
                    answerResponseDto.setAnswerDate(answer.getAnswerDate());
                    answerResponseDto.setAnswerTime(answer.getAnswerTime());
                    answerResponseDto.setQuestionDto(new QuestionDto(answer.getQuestion().getQuestionId(), answer.getQuestion().getUserQuestion()));
                    answerResponseDto.setNoLikes(Long.valueOf(answer.getLikeAnswers().size()));
                    answerResponseDto.setNoComments(Long.valueOf(answer.getCommentOnAnswers().size()));
                    answerResponseDto.setUserDto(new UserResponseDto(answer.getUserEntity().getUserId(), answer.getUserEntity().getUserName(), answer.getUserEntity().getProfilePath(),followOrNot(null, answer.getUserEntity())));
                    answerResponseDtos.add(answerResponseDto);
                });   
            }
            return answerResponseDtos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashSet<>();
    }
    
    private boolean followOrNot(UserEntity loginUser, UserEntity postUser) {
        if(loginUser==null) return false;
        for (Follow follow : loginUser.getFollowing()) {
            if (follow.getToUser().equals(postUser)) {
                return true;
            }
        }
        return false;
    }

    private boolean likedOrNot(List<LikeAnswer> likeAnswers, UserEntity userEntity) {
        for (LikeAnswer likeAnswer : likeAnswers) {
            if (likeAnswer.getUserEntity().equals(userEntity)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Answer> getUserAnswers(UserEntity profileUser, int pageNo) {
        try {
            Pageable pageable = PageRequest.of(pageNo-1, 2,Sort.by("answerDate").and(Sort.by("answerTime")));    
            List<Answer> answers = answerRepository.getUserAnswers(profileUser,pageable).getContent();
            return answers;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    
    
    
    
    
    
    
    
    
    
    
    

}
