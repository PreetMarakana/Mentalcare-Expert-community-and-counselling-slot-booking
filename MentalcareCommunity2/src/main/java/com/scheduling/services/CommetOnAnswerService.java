package com.scheduling.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.scheduling.dto.AnswerCommentDto;
import com.scheduling.dto.CommentOnAnswerResponseDto;
import com.scheduling.dto.CommentResponseDto;
import com.scheduling.model.CommentOnAnswer;

@Service
public interface CommetOnAnswerService {

    CommentOnAnswerResponseDto createAnswercomment(AnswerCommentDto answerCommentDto, Long answerId, String string);

    boolean deleteCommment(Long commentId, String username);

    CommentOnAnswer getCommentOnAnswerById(Long answerCommentId);

    List<CommentResponseDto> getAllCommentsOfAnswer(Long answerId, UserDetails details);



}
