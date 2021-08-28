package com.scheduling.dto;

import java.sql.Date;
import java.sql.Time;

public class CommentOnPostResponseDto {
    
    private Long commentId;
    
    private Long postId;
    
    private CommentOnPostDto commentOnPostDto;
    
    private UserResponseDto userResponseDto;
    
    private Date commentDate;
    
    private Time commentTime;

    public CommentOnPostResponseDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CommentOnPostResponseDto(Long postId, CommentOnPostDto commentOnPostDto, UserResponseDto userResponseDto,
            Date commentDate, Time commentTime) {
        super();
        this.postId = postId;
        this.commentOnPostDto = commentOnPostDto;
        this.userResponseDto = userResponseDto;
        this.commentDate = commentDate;
        this.commentTime = commentTime;
    }
    
    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public CommentOnPostDto getCommentOnPostDto() {
        return commentOnPostDto;
    }

    public void setCommentOnPostDto(CommentOnPostDto commentOnPostDto) {
        this.commentOnPostDto = commentOnPostDto;
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

    @Override
    public String toString() {
        return "CoomentOnPostResponseDto [postId=" + postId + ", commentOnPostDto=" + commentOnPostDto
                + ", userResponseDto=" + userResponseDto + ", commentDate=" + commentDate + ", commentTime="
                + commentTime + "]";
    }

}