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
public class LikeAnswerComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeAnswerComment;

    @ManyToOne
    @JsonBackReference
    private CommentOnAnswer commentOnAnswer;
    
    @ManyToOne
    @JsonBackReference
    private UserEntity userEntity;
    
    private Date commnentDate;

    private Time commentTime;

    public LikeAnswerComment() {
        super();
        // TODO Auto-generated constructor stub
    }

    public LikeAnswerComment(Long likeAnswerComment, CommentOnAnswer commentOnAnswer, UserEntity userEntity,
            Date commnentDate, Time commentTime) {
        super();
        this.likeAnswerComment = likeAnswerComment;
        this.commentOnAnswer = commentOnAnswer;
        this.userEntity = userEntity;
        this.commnentDate = commnentDate;
        this.commentTime = commentTime;
    }

    public Long getLikeAnswerComment() {
        return likeAnswerComment;
    }

    public void setLikeAnswerComment(Long likeAnswerComment) {
        this.likeAnswerComment = likeAnswerComment;
    }

    public CommentOnAnswer getCommentOnAnswer() {
        return commentOnAnswer;
    }

    public void setCommentOnAnswer(CommentOnAnswer commentOnAnswer) {
        this.commentOnAnswer = commentOnAnswer;
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
        return "LikeAnswerComment [likeAnswerComment=" + likeAnswerComment + ", commentOnAnswer=" + commentOnAnswer
                + ", userEntity=" + userEntity + ", commnentDate=" + commnentDate + ", commentTime=" + commentTime
                + "]";
    }
    
}

