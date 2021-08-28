package com.scheduling.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.scheduling.dto.CommentPostRepliesRequestDto;
import com.scheduling.dto.CommentRepliesDto;

@Service
public interface CommentOnPostReplieService {

    boolean createComment(Long commentId, CommentPostRepliesRequestDto commentPostRepliesRequestDto, String username);

    boolean deleteCommentReply(Long replyId, String username);

    List<CommentRepliesDto> getcommentsreplies(Long commentsId, UserDetails userDetails);

}
