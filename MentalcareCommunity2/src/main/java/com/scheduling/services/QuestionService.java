package com.scheduling.services;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.scheduling.dto.QuestionDto;
import com.scheduling.dto.QuestionResponseDto;
import com.scheduling.model.Question;
import com.scheduling.model.UserEntity;

@Service
public interface QuestionService {

    QuestionResponseDto createQuestion(QuestionDto questionDto, String username);

    boolean checkQuestionUser(String username, long questionId);

    QuestionResponseDto getQuestionByIdDto(long questionId, UserDetails userDetails);

    List<QuestionResponseDto> getQuestionList();

    Question getQuestionById(long qid);

    Set<QuestionResponseDto> getQuestionFeed(UserEntity userEntity, Integer pageNo);

    List<Question> getUserQuestions(UserEntity profileUser, int i);


}
