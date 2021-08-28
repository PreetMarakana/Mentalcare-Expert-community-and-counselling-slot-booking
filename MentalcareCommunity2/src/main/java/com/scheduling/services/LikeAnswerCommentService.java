package com.scheduling.services;

import org.springframework.stereotype.Service;

@Service
public interface LikeAnswerCommentService {

    String createLike(Long answerCommentId, String username);

}
