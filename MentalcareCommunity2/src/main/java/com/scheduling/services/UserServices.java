package com.scheduling.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.scheduling.dto.ChangePasswordDto;
import com.scheduling.dto.FacebookLoginRequest;
import com.scheduling.dto.MobileTokenDto;
import com.scheduling.dto.ReportDto;
import com.scheduling.dto.UserDetailsUpdate;
import com.scheduling.dto.UserDto;
import com.scheduling.model.Updateprofile;
import com.scheduling.model.UserEntity;

@Service
public interface UserServices {

    boolean userExistOrNot(String userEmail);

    long createUser(UserDto userDto);

    UserEntity getUserByUserId(long userId);

    void createUser(UserEntity userEntity);

    UserEntity getUserByEmail(String username);

    UserDto getUserByEmailForDto(String username);

    UserDto getUserByUserIdDto(long userId);

    boolean changePassword(ChangePasswordDto changePasswordDto,String username);

    boolean updateUser(UserDetailsUpdate detailsUpdate, UserDetails userDetails);

    String getTokenOrRegisterFacebookLogin(FacebookLoginRequest loginrequest);

    void saveUser(UserEntity userEntity);

    Long getUseridUsingEmail(String username);
    
    boolean updateUserPhoto(Updateprofile updateprofile,UserDetails userDetails);

    boolean updateToken(MobileTokenDto tokenDto, UserDetails userDetails);

    void submitReport(ReportDto reportDto, UserDetails userDetails);

}
