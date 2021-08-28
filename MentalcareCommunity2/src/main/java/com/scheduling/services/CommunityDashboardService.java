package com.scheduling.services;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.scheduling.dto.FeedsDto;
import com.scheduling.dto.UserProfileDto;

@Service
public interface CommunityDashboardService {

    Set<FeedsDto> getFeeds(UserDetails userDetails, Integer pageNo);

    UserProfileDto getuserProfile(Long userId,UserDetails userDetails,int pageNo);

}
