package com.scheduling.dto;

import java.sql.Date;
import java.sql.Time;

public class CommentResponseDto {

    private Long commentId;
    
    private String comment;
    
    private UserResponseDto userResponseDto;

    private Long noLikes;
    
    private Long noComments;
    
    private Date postDate;
    
    private Time postTime;
    
    private boolean liked;

    
    
    public CommentResponseDto() {
        super();
        // TODO Auto-generated constructor stub
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

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public CommentResponseDto(Long commentId, String comment, UserResponseDto userResponseDto, Long noLikes,
            Long noComments, Date postDate, Time postTime, boolean liked) {
        super();
        this.commentId = commentId;
        this.comment = comment;
        this.userResponseDto = userResponseDto;
        this.noLikes = noLikes;
        this.noComments = noComments;
        this.postDate = postDate;
        this.postTime = postTime;
        this.liked = liked;
    }

    @Override
    public String toString() {
        return "CommentResponseDto [commentId=" + commentId + ", comment=" + comment + ", userResponseDto="
                + userResponseDto + ", noLikes=" + noLikes + ", noComments=" + noComments + ", postDate=" + postDate
                + ", postTime=" + postTime + ", liked=" + liked + "]";
    }
    
}
