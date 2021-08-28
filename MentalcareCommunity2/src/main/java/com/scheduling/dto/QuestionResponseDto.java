package com.scheduling.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class QuestionResponseDto {

    private long questionId;

    private QuestionDto questionDto;

    private List<AnswerResponseDto> answerDtos;

    private UserResponseDto userResponseDto;

    private Time questionTime;

    private Date questionDate;

    private Long noAnswer;
    
    public QuestionResponseDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public QuestionResponseDto(QuestionDto questionDto, List<AnswerResponseDto> answerDtos,
            UserResponseDto userResponseDto, Time questionTime, Date questionDate) {
        super();
        this.questionDto = questionDto;
        this.answerDtos = answerDtos;
        this.userResponseDto = userResponseDto;
        this.questionTime = questionTime;
        this.questionDate = questionDate;
    }

    public QuestionDto getQuestionDto() {
        return questionDto;
    }

    public void setQuestionDto(QuestionDto questionDto) {
        this.questionDto = questionDto;
    }

    public List<AnswerResponseDto> getAnswerDtos() {
        return answerDtos;
    }

    public void setAnswerDtos(List<AnswerResponseDto> answerDtos) {
        this.answerDtos = answerDtos;
    }

    public UserResponseDto getUserResponseDto() {
        return userResponseDto;
    }

    public void setUserResponseDto(UserResponseDto userResponseDto) {
        this.userResponseDto = userResponseDto;
    }

    public Time getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(Time questionTime) {
        this.questionTime = questionTime;
    }

    public Date getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(Date questionDate) {
        this.questionDate = questionDate;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
    
    public Long getNoAnswer() {
        return noAnswer;
    }

    public void setNoAnswer(Long noAnswer) {
        this.noAnswer = noAnswer;
    }

    @Override
    public String toString() {
        return "QuestionResponseDto [questionId=" + questionId + ", questionDto=" + questionDto + ", answerDtos="
                + answerDtos + ", userResponseDto=" + userResponseDto + ", questionTime=" + questionTime
                + ", questionDate=" + questionDate + "]";
    }

}
