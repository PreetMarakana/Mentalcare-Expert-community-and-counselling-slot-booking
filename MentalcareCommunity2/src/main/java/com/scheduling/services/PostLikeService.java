package com.scheduling.services;

import org.springframework.stereotype.Service;

@Service
public interface PostLikeService  {

    String postLike(Long postId, String username);

}
