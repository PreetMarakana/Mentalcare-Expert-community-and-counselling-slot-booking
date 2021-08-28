package com.scheduling.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class LikePostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeCommentId;
    
    @ManyToOne
    @JsonBackReference
    private CommentOnPost commentOnPost;
    
    @ManyToOne
    @JsonBackReference
    private UserEntity userEntity;

    private Date commnentDate;

    private Time commentTime;

    public LikePostComment() {
        super();
        // TODO Auto-generated constructor stub
    }

    public LikePostComment(Long likeCommentId, CommentOnPost commentOnPost, UserEntity userEntity, Date commnentDate,
            Time commentTime) {
        super();
        this.likeCommentId = likeCommentId;
        this.commentOnPost = commentOnPost;
        this.userEntity = userEntity;
        this.commnentDate = commnentDate;
        this.commentTime = commentTime;
    }

    public Long getLikeCommentId() {
        return likeCommentId;
    }

    public void setLikeCommentId(Long likeCommentId) {
        this.likeCommentId = likeCommentId;
    }

    public CommentOnPost getCommentOnPost() {
        return commentOnPost;
    }

    public void setCommentOnPost(CommentOnPost commentOnPost) {
        this.commentOnPost = commentOnPost;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
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
        return "LikePostComment [likeCommentId=" + likeCommentId + ", commentOnPost=" + commentOnPost + ", userEntity="
                + userEntity + ", commnentDate=" + commnentDate + ", commentTime=" + commentTime + "]";
    }
    
}
