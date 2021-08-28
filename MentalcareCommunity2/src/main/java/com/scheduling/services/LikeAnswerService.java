package com.scheduling.services;

import org.springframework.stereotype.Service;

@Service
public interface LikeAnswerService {

    String likeAnswer(Long answerId, String username);

}
