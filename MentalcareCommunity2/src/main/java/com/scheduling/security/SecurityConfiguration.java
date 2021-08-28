package com.scheduling.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.google.common.collect.ImmutableList;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private CustomAuthenticationprovider customAuthenticationprovider;
    
    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler;
    
    @Autowired
    private CustomAuthenticationSuccessHandler  authenticationSuccessHandler;
    
    @Autowired
    private Jwtfilter filter;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customAuthenticationprovider);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http
        .httpBasic().disable()
        .cors()
        .and()
        .addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class)
//        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
        .authorizeRequests()
        .antMatchers("/registration/**","/login/**").permitAll()
        .antMatchers("/secure/**").authenticated()
        .antMatchers("/community/dashboard/secure/**").authenticated()
        .antMatchers("/login/changepass").authenticated()
        .and()
        .csrf().disable()
         .formLogin()
           .loginPage("/login/login")  
           .loginProcessingUrl("/loginrequest").permitAll()
           .successHandler(authenticationSuccessHandler)
           .failureHandler(authenticationFailureHandler)
           .and()
           .logout()
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .clearAuthentication(true)
            .logoutRequestMatcher( new AntPathRequestMatcher("/logoutrequest"))
            .logoutSuccessUrl("/login/logout").permitAll()
        .and()
            .rememberMe()
        .and()
        .sessionManagement()                          
        .maximumSessions(1)                         
        .maxSessionsPreventsLogin(false)          
        .expiredUrl("/login/login?expired");  

    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ImmutableList.of("*"));
        configuration.setAllowedMethods(ImmutableList.of("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH"));
//        configuration.setAllowCredentials(true);
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
//        configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
