package com.scheduling.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scheduling.model.UserEntity;
import com.scheduling.services.DeviceService;
import com.scheduling.services.UserServices;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private DeviceService deviceService;
    
    @Autowired
    private UserServices userService;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        setDefaultTargetUrl("/login/loginsuccess?username="+authentication.getName());
//        loginNotification(authentication,request);
        super.onAuthenticationSuccess(request, response, authentication);
    }
    private void loginNotification(Authentication authentication, HttpServletRequest request) {
        try {
            
            UserDetails userDetails = (UserDetails)authentication.getPrincipal();
            UserEntity userEntity = userService.getUserByEmail(userDetails.getUsername());
            System.out.println(userEntity); 
            if (authentication.getPrincipal() != null) {
                deviceService.verifyDevice(userEntity,request);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
