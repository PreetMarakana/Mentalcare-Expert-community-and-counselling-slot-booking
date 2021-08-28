package com.scheduling.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.scheduling.dto.AnswerDto;
import com.scheduling.dto.AnswerResponseDto;
import com.scheduling.model.Answer;
import com.scheduling.model.UserEntity;

@Service
public interface AnswerService {

    AnswerResponseDto createAnswer(long qid, AnswerDto answerDto, String username);

    boolean deleteAnswer(String username, long aid);

    Answer getanswerByAnswerId(Long answerId);

    Set<AnswerResponseDto> getAnswerFeeds(UserEntity userEntity, Integer pageNo);

    List<Answer> getUserAnswers(UserEntity profileUser, int pageNo);

    
}
