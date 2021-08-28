package com.scheduling.serviceImplimentation;

import java.security.SecureRandom;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.scheduling.Exception.SqlQueryException;
import com.scheduling.Exception.UserNotExists;
import com.scheduling.dto.ChangePasswordDto;
import com.scheduling.dto.FacebookLoginRequest;
import com.scheduling.dto.FacebookUser;
import com.scheduling.dto.MobileTokenDto;
import com.scheduling.dto.ReportDto;
import com.scheduling.dto.UserDetailsUpdate;
import com.scheduling.dto.UserDto;
import com.scheduling.model.Answer;
import com.scheduling.model.Post;
import com.scheduling.model.Question;
import com.scheduling.model.Updateprofile;
import com.scheduling.model.UserEntity;
import com.scheduling.repository.UserEntityRepository;
import com.scheduling.security.Jwtutil;
import com.scheduling.services.AnswerService;
import com.scheduling.services.PostService;
import com.scheduling.services.QuestionService;
import com.scheduling.services.ReportService;
import com.scheduling.services.UserServices;

@Service
public class UserServiceImplimentation implements UserServices {

    @Autowired
    private FacebookClient facebookClient;
    
    @Autowired
    private UserEntityRepository userEntityRepository;
    
    @Autowired
    private Jwtutil jwtutil;
    
    @Autowired
    private EmailSender emailSender;
    
    @Autowired
    private PostService postService;
    
    @Autowired
    private QuestionService questionService;
    
    @Autowired
    private AnswerService answerService;
    
    @Autowired
    private ReportService reportService;
    
    @Override
    public boolean userExistOrNot(String userEmail) {
        
       Optional<UserEntity> userEntity =   userEntityRepository.findByuserEmailAnduserVerify(userEmail.toLowerCase());
       
       if (userEntity.isPresent()) {
           return true;
       }else {
           return false;
       }
    }

    @Override
    public long createUser(UserDto userDto) {
        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserEmail(userDto.getUserEmail().toLowerCase());
            userEntity.setUserName(userDto.getUserName());
            userEntity.setUserPassword(userDto.getUserPassword());
            return userEntityRepository.save(userEntity).getUserId();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlQueryException("user creation sql exception");
        }
    }

    @Override
    public UserEntity getUserByUserId(long userId) {
        try {
            Optional<UserEntity> user = userEntityRepository.findById(userId);
            if (user.isPresent()) {
                return user.get();
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserNotExists("this user id not exists");
        }
    }

    @Override
    public void createUser(UserEntity userEntity) {
        try {
            userEntityRepository.save(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlQueryException("user creation sql exception");
        }
    }

    @Override
    public UserEntity getUserByEmail(String username) {
        Optional<UserEntity>  user = userEntityRepository.findByuserEmailAnduserVerify(username.toLowerCase());
        if (user.isPresent()) {
            return user.get();
        }else {
            return null;
        }
    }

    @Override
    public UserDto getUserByEmailForDto(String username) {
        Optional<UserEntity>  user = userEntityRepository.findByuserEmailAnduserVerify(username.toLowerCase());
        if (user.isPresent()) {
            return new UserDto(user.get().getUserEmail(),user.get().isUserVerify(),user.get().getUserId());
        }else {
            return null;
        }
        
    }

    @Override
    public UserDto getUserByUserIdDto(long userId) {
        try {
            Optional<UserEntity> userEntity =   userEntityRepository.findById(userId);
            if (userEntity.isPresent()) {
                return new UserDto(userEntity.get().getUserName(), userEntity.get().getUserEmail().toLowerCase());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    public boolean changePassword(ChangePasswordDto changePasswordDto,String email) {
        try {
            UserEntity userEntity = getUserByEmail(email.toLowerCase());
            
            if (userEntity!=null) {
                userEntity.setUserPassword(changePasswordDto.getPassword());
                userEntityRepository.save(userEntity);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean updateUser(UserDetailsUpdate detailsUpdate, UserDetails userDetails) {
        
        try {
            UserEntity userEntity = getUserByEmail(userDetails.getUsername().toLowerCase());
            
            if (detailsUpdate.getUsername()!=null) {
                userEntity.setUserName(detailsUpdate.getUsername());
            }
            userEntityRepository.save(userEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getTokenOrRegisterFacebookLogin(FacebookLoginRequest loginrequest) {
        
        try {
            FacebookUser facebookUser = facebookClient.getUser(loginrequest.getAccessToken());
            
            UserEntity userEntity = userEntityRepository.getUserByFacebookId(facebookUser.getId());
            
            if (userEntity==null) {
                UserEntity converto = converto(facebookUser);
                createUser(converto);
                return jwtutil.generateToken(converto.getUserEmail().toLowerCase());
            }else {
                return jwtutil.generateToken(userEntity.getUserEmail().toLowerCase());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private UserEntity converto(FacebookUser facebookUser) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(facebookUser.getFirstName()+" "+facebookUser.getLastName());
        userEntity.setUserEmail(facebookUser.getEmail());
        userEntity.setUserPassword(generatePassword(8));
        userEntity.setProfilePath(facebookUser.getPicture().getData().getUrl());
        userEntity.setFaceBookId(facebookUser.getId());
        userEntity.setUserVerify(true);
        return userEntity;
    }
    private String generatePassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        SecureRandom random = new SecureRandom();
        char[] password = new char[length];

        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));

        for(int i = 4; i< length ; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return new String(password);
    }

    @Override
    public void saveUser(UserEntity userEntity) {
        try {
            if (userEntity!=null) {
                userEntityRepository.save(userEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long getUseridUsingEmail(String username) {
        UserEntity entity = getUserByEmail(username);
        if (entity!=null) {
            return entity.getUserId();
        }
        return null;
    }

    @Override
    public boolean updateUserPhoto(Updateprofile updateprofile, UserDetails userDetails) {
        
        try {
            UserEntity userEntity = getUserByEmail(userDetails.getUsername().toLowerCase());

            if (updateprofile.getProfilePath()!=null) {
               String link = AwsService.uploadimage(updateprofile.getProfilePath().getOriginalFilename(), updateprofile.getProfilePath().getBytes());
                userEntity.setProfilePath(link);
            }
            
            userEntityRepository.save(userEntity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }   
        return false;
    }

    @Override
    public boolean updateToken(MobileTokenDto tokenDto,UserDetails userDetails) {
        
        try {
            UserEntity userEntity = getUserByEmail(userDetails.getUsername().toLowerCase());
            if (tokenDto.getToken()!=null) {
                userEntity.setMobileToken(tokenDto.getToken());
                userEntityRepository.save(userEntity);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            
        return false;
    }
    
    @Override
    public void submitReport(ReportDto reportDto,UserDetails userDetails) {
        try {
            if(reportDto.getType().equals("post")) {
                Post post = postService.getPostById(Long.parseLong(reportDto.getId()));
                emailSender.sendReportEmail(post.getUserEntity().getUserEmail(),"");
            }else if (reportDto.getType().equals("question")) {
                Question question = questionService.getQuestionById(Long.parseLong(reportDto.getId()));
                emailSender.sendReportEmail(question.getUserEntity().getUserEmail(),question.getUserQuestion());
            }else {
                Answer answer = answerService.getanswerByAnswerId(Long.parseLong(reportDto.getId()));
                emailSender.sendReportEmail(answer.getUserEntity().getUserEmail(),answer.getUserAnswer());
            }
            reportService.saveData(reportDto,userDetails);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

}








