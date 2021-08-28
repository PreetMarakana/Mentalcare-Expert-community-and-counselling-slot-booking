package com.scheduling.dto;

import java.sql.Date;
import java.sql.Time;

public class CommentOnAnswerResponseDto {

    private Long commentId;
    
    private AnswerDto answerDto;
    
    private AnswerCommentDto answerCommentDto;
    
    private UserResponseDto userResponseDto;
    
    private Date commentDate;
    
    private Time commentTime;

    public CommentOnAnswerResponseDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CommentOnAnswerResponseDto(AnswerCommentDto answerCommentDto, UserResponseDto userResponseDto,
            Date commentDate, Time commentTime) {
        super();
        this.answerCommentDto = answerCommentDto;
        this.userResponseDto = userResponseDto;
        this.commentDate = commentDate;
        this.commentTime = commentTime;
    }

    public AnswerCommentDto getAnswerCommentDto() {
        return answerCommentDto;
    }

    public void setAnswerCommentDto(AnswerCommentDto answerCommentDto) {
        this.answerCommentDto = answerCommentDto;
    }

    public UserResponseDto getUserResponseDto() {
        return userResponseDto;
    }

    public void setUserResponseDto(UserResponseDto userResponseDto) {
        this.userResponseDto = userResponseDto;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Time getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Time commentTime) {
        this.commentTime = commentTime;
    }
    
    public AnswerDto getAnswerDto() {
        return answerDto;
    }

    public void setAnswerDto(AnswerDto answerDto) {
        this.answerDto = answerDto;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    @Override
    public String toString() {
        return "CommentOnAnswerResponseDto [answerCommentDto=" + answerCommentDto + ", userResponseDto="
                + userResponseDto + ", commentDate=" + commentDate + ", commentTime=" + commentTime + "]";
    }
    
    
}
