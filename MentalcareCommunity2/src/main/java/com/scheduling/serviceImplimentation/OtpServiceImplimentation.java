package com.scheduling.serviceImplimentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scheduling.Exception.SqlQueryException;
import com.scheduling.dto.OtpDto;
import com.scheduling.dto.UserDto;
import com.scheduling.model.OneTimePassword;
import com.scheduling.model.UserEntity;
import com.scheduling.repository.OtpRepository;
import com.scheduling.services.EmailService;
import com.scheduling.services.OtpService;
import com.scheduling.services.UserServices;

@Service
public class OtpServiceImplimentation implements OtpService  {

    @Autowired
    private UserServices userService;
   
    @Autowired
    private OtpRepository otpRespository;
    
    @Autowired
    private EmailService emailService;
    
    @Override
    public OtpDto createOtp(long userId,UserDto userDto) {
        try {
            UserEntity userEntity = userService.getUserByUserId(userId);
            OneTimePassword oneTimePassword1 = otpRespository.findByuserEntity(userEntity);
            if (oneTimePassword1!=null) {
                otpRespository.delete(oneTimePassword1);
            }
            if (userDto!=null) {
                userEntity.setUserName(userDto.getUserName());
                userEntity.setUserPassword(userDto.getUserPassword());
                userService.saveUser(userEntity);
            }
            OneTimePassword oneTimePassword = new OneTimePassword(userEntity);
            otpRespository.save(oneTimePassword);
            return new OtpDto(oneTimePassword.getOtpValue());
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlQueryException("otp creation sql exception");
        }
    }

    @Override
    public boolean verifyOtp(String otpValue) {
        try {
            OneTimePassword oneTimePassword = otpRespository.findByotpValue(otpValue);
            if (oneTimePassword==null) {
                return false;
            }else {
                UserEntity userEntity = oneTimePassword.getUserEntity();
                if (userEntity!=null) {
                    userEntity.setUserVerify(true);
                    userService.createUser(userEntity);
                    otpRespository.deleteById(oneTimePassword.getOtpId());
                    return true;
                }else {
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlQueryException("otp verification sql exception");
        }
    }

    @Override
    public boolean resendOtp(long uid) {
        try {
            UserEntity userEntity = userService.getUserByUserId(uid);
            if (userEntity!=null) {
                OneTimePassword oneTimePassword = otpRespository.findByuserEntity(userEntity);
                if (oneTimePassword!=null) {
                    otpRespository.deleteById(oneTimePassword.getOtpId());
                    OneTimePassword newOneTimePassword = new OneTimePassword(userEntity);
                    otpRespository.save(newOneTimePassword);
                    emailService.sendmail(userEntity.getUserEmail(),newOneTimePassword.getOtpValue());
                }else {
                    OneTimePassword newOneTimePassword = new OneTimePassword(userEntity);
                    otpRespository.save(newOneTimePassword);
                    emailService.sendmail(userEntity.getUserEmail(),newOneTimePassword.getOtpValue());
                }
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlQueryException("redend otp sql exception");
        }
    }
    
    @Override
    public String verifyOtpForForget(String otpValue) {
        try {
            OneTimePassword oneTimePassword = otpRespository.findByotpValue(otpValue);
            if (oneTimePassword==null) {
                return "";
            }else {
                UserEntity userEntity = oneTimePassword.getUserEntity();
                if (userEntity!=null) {
                    otpRespository.deleteById(oneTimePassword.getOtpId());
                    return userEntity.getUserEmail();
                }else {
                    return "";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlQueryException("otp verification sql exception");
        }
    }
    
}
