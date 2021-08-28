package com.scheduling.dto;

import java.sql.Date;
import java.sql.Time;

public class CommentRepliesDto {

    private Long commentId;
    
    private String comment;
    
    private UserResponseDto userResponseDto;

    private Date postDate;
    
    private Time postTime;

    public CommentRepliesDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CommentRepliesDto(Long commentId, String comment, UserResponseDto userResponseDto, Date postDate,
            Time postTime) {
        super();
        this.commentId = commentId;
        this.comment = comment;
        this.userResponseDto = userResponseDto;
        this.postDate = postDate;
        this.postTime = postTime;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UserResponseDto getUserResponseDto() {
        return userResponseDto;
    }

    public void setUserResponseDto(UserResponseDto userResponseDto) {
        this.userResponseDto = userResponseDto;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Time getPostTime() {
        return postTime;
    }

    public void setPostTime(Time postTime) {
        this.postTime = postTime;
    }

    @Override
    public String toString() {
        return "CommentRepliesDto [commentId=" + commentId + ", comment=" + comment + ", userResponseDto="
                + userResponseDto + ", postDate=" + postDate + ", postTime=" + postTime + "]";
    }
    
}
