package com.scheduling.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.scheduling.dto.CommentOnPostDto;
import com.scheduling.dto.CommentOnPostResponseDto;
import com.scheduling.dto.CommentResponseDto;
import com.scheduling.model.CommentOnPost;

@Service
public interface CommentOnPostService {

    CommentOnPostResponseDto createcommentOnPost(Long postId, CommentOnPostDto commentOnPostDto, String username);

    boolean deletecommentOnPost(Long commentId, String username);

    CommentOnPost getCommentByCommentIdOnPost(Long commentId);

    List<CommentResponseDto> getAllCommentsOfPost(Long postId, UserDetails details);

}
