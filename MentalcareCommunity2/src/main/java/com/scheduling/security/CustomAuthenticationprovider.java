package com.scheduling.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.scheduling.Exception.LoginException;
import com.scheduling.Exception.UserNotExists;
import com.scheduling.model.UserEntity;
import com.scheduling.serviceImplimentation.LoginAttemptService;
import com.scheduling.services.OtpService;
import com.scheduling.services.UserServices;

@Component
public class CustomAuthenticationprovider implements AuthenticationProvider {

    @Autowired
    private UserServices userService;
    
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private LoginAttemptService loginAttemptService;
    
    @Autowired
    private OtpService otpService;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
        
        UsernamePasswordAuthenticationToken authenticationToken = null;
        String ip = getClientIP();
        String username = authentication.getName().toLowerCase();
        String password = authentication.getCredentials().toString();
        
        if (loginAttemptService.isBlocked(ip)) {
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {

                        @Override
                        public void run() {
                           loginAttemptService.loginSucceeded(ip);
                            
                        }
                        
                    }, 1000*(long)60);
            throw new LoginException("too much try , again try after 10 minutes");
        }
        
        UserEntity userEntity = userService.getUserByEmail(username.toLowerCase());
          
        if (userEntity!=null) {
            if (!userEntity.isUserVerify()) {
                otpService.resendOtp(userEntity.getUserId());
                throw new UserNotExists("Verify your email");
            }
            if (userEntity.getUserEmail().equals(username.toLowerCase()) && userEntity.getUserPassword().equals(password) && userEntity.isUserVerify()) {
                List<GrantedAuthority> grantedAuths = new ArrayList<>();
                grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
                authenticationToken = new UsernamePasswordAuthenticationToken(new User(username, password, grantedAuths), password,grantedAuths);
            }
        }else {
              throw new UserNotExists("Invalid credentials");
         }
        
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
