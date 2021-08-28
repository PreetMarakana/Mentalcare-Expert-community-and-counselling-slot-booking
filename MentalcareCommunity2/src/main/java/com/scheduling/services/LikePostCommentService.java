package com.scheduling.services;

import org.springframework.stereotype.Service;

@Service
public interface LikePostCommentService {

    String createLike(Long commentId, String username);

}
