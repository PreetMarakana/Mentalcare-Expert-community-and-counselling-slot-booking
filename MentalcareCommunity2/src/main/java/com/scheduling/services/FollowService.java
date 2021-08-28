package com.scheduling.services;

import org.springframework.stereotype.Service;

@Service
public interface FollowService {

    String followUser(Long userId, String username);

}
