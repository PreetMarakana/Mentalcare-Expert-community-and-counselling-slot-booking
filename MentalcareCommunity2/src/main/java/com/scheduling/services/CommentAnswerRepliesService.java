package com.scheduling.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.scheduling.dto.CommentAnswerRepliesRequestDto;
import com.scheduling.dto.CommentRepliesDto;

@Service
public interface CommentAnswerRepliesService {

    boolean createComment(String username, Long commentId, CommentAnswerRepliesRequestDto answerRepliesRequestDto);

    boolean deleteCommentReplies(Long commentReplyId, String string);

    List<CommentRepliesDto> getcommentsreplies(Long commentsId, UserDetails userDetails);

}
