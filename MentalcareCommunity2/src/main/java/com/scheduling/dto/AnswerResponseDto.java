package com.scheduling.dto;

import java.sql.Date;
import java.sql.Time;

public class AnswerResponseDto {
    
    private long answerId;
    
    private QuestionDto questionDto;
    
    private AnswerDto answerDto;
    
    private UserResponseDto userDto;
    
    private Time answerTime;
    
    private Date answerDate;

    private Long noLikes;

    private Long noComments;
    
    private boolean liked;
    
    public AnswerResponseDto() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public AnswerResponseDto(QuestionDto questionDto, AnswerDto answerDto, UserResponseDto userDto, Time answerTime,
            Date answerDate) {
        super();
        this.questionDto = questionDto;
        this.answerDto = answerDto;
        this.userDto = userDto;
        this.answerTime = answerTime;
        this.answerDate = answerDate;
    }
    public AnswerDto getAnswerDto() {
        return answerDto;
    }

    public void setAnswerDto(AnswerDto answerDto) {
        this.answerDto = answerDto;
    }

    public UserResponseDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserResponseDto userDto) {
        this.userDto = userDto;
    }


    public Time getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Time answerTime) {
        this.answerTime = answerTime;
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

    public QuestionDto getQuestionDto() {
        return questionDto;
    }

    public void setQuestionDto(QuestionDto questionDto) {
        this.questionDto = questionDto;
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    @Override
    public String toString() {
        return "AnswerResponseDto [answerDto=" + answerDto + ", userDto=" + userDto + ", answerTime=" + answerTime
                + ", answerDate=" + answerDate + "]";
    }

    public AnswerResponseDto(long answerId, QuestionDto questionDto, AnswerDto answerDto, UserResponseDto userDto,
            Time answerTime, Date answerDate) {
        super();
        this.answerId = answerId;
        this.questionDto = questionDto;
        this.answerDto = answerDto;
        this.userDto = userDto;
        this.answerTime = answerTime;
        this.answerDate = answerDate;
    }

    public Long getNoLikes() {
        return noLikes;
    }

    public void setNoLikes(Long noLikes) {
        this.noLikes = noLikes;
    }

    public Long getNoComments() {
        return noComments;
    }

    public void setNoComments(Long noComments) {
        this.noComments = noComments;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

}    
