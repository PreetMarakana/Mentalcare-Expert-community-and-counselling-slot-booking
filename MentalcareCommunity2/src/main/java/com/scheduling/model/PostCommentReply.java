package com.scheduling.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class PostCommentReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postCommentReplyId;
    
    @NotEmpty
    private String commentText;
    
    @ManyToOne
    @JsonBackReference
    private UserEntity userEntity;
    
    @ManyToOne
    @JsonBackReference
    private CommentOnPost CommentOnPost;

    private Date commnentDate;

    private Time commentTime;

    public PostCommentReply() {
        super();
        // TODO Auto-generated constructor stub
    }

    public PostCommentReply(Long postCommentReplyId, @NotEmpty String commentText, UserEntity userEntity,
            com.scheduling.model.CommentOnPost commentOnPost, Date commnentDate, Time commentTime) {
        super();
        this.postCommentReplyId = postCommentReplyId;
        this.commentText = commentText;
        this.userEntity = userEntity;
        CommentOnPost = commentOnPost;
        this.commnentDate = commnentDate;
        this.commentTime = commentTime;
    }

    public Long getPostCommentReplyId() {
        return postCommentReplyId;
    }

    public void setPostCommentReplyId(Long postCommentReplyId) {
        this.postCommentReplyId = postCommentReplyId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public CommentOnPost getCommentOnPost() {
        return CommentOnPost;
    }

    public void setCommentOnPost(CommentOnPost commentOnPost) {
        CommentOnPost = commentOnPost;
    }

    public Date getCommnentDate() {
        return commnentDate;
    }

    public void setCommnentDate(Date commnentDate) {
        this.commnentDate = commnentDate;
    }

    public Time getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Time commentTime) {
        this.commentTime = commentTime;
    }

    @Override
    public String toString() {
        return "PostCommentReply [postCommentReplyId=" + postCommentReplyId + ", commentText=" + commentText
                + ", userEntity=" + userEntity + ", CommentOnPost=" + CommentOnPost + ", commnentDate=" + commnentDate
                + ", commentTime=" + commentTime + "]";
    }

    
}
