package com.scheduling.services;

import org.springframework.stereotype.Service;

import com.scheduling.dto.OtpDto;
import com.scheduling.dto.UserDto;

@Service
public interface OtpService {

    OtpDto createOtp(long userId, UserDto userDto);

    boolean verifyOtp(String otpValue);

    boolean resendOtp(long uid);
    
    String verifyOtpForForget(String otpValue);
}
