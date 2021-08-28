package com.scheduling.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.scheduling.model.UserEntity;
import com.scheduling.services.UserServices;

@Component
public class Jwtfilter  extends OncePerRequestFilter {

    @Autowired
    private UserServices userService;
    
    @Autowired
    private Jwtutil jwtutil;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String header = request.getHeader("Authorization");
        
        String jwt=null;
        String username = null;
        
        if(header!=null && header.startsWith("Bearer ")) {
            jwt=header.substring(7);
            username=jwtutil.getUsernameFromToken(jwt);
        }
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
            UserEntity ud = userService.getUserByEmail(username);
            if(Boolean.TRUE.equals(jwtutil.validateToken(jwt,username))) {
                List<GrantedAuthority> grantedAuths = new ArrayList<>();
                grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =  new UsernamePasswordAuthenticationToken(new User(username, ud.getUserPassword(), grantedAuths), ud.getUserPassword(),grantedAuths);
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

}
