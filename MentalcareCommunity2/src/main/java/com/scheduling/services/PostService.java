package com.scheduling.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.scheduling.dto.PostDto;
import com.scheduling.dto.PostResponseDto;
import com.scheduling.model.Post;
import com.scheduling.model.UserEntity;

@Service
public interface PostService {

    PostResponseDto createPost(PostDto postDto, String username);

    boolean deletePost(Long pid, String username);

    Post getPostById(Long postId);

    Set<PostResponseDto> getPostForFeeds(UserEntity userEntity, Integer pageNo);

    List<Post> getUserPosts(UserEntity profileUser, int pageNo);

}
