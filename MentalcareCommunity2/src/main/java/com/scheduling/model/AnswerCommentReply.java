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
public class AnswerCommentReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerCommentReplyId;
    
    @NotEmpty
    private String commentText;
    
    @ManyToOne
    @JsonBackReference
    private UserEntity userEntity;
    
    @ManyToOne
    @JsonBackReference
    private  CommentOnAnswer commentOnAnswer;

    private Date commnentDate;

    private Time commentTime;

    public AnswerCommentReply() {
        super();
        // TODO Auto-generated constructor stub
    }

    public AnswerCommentReply(Long answerCommentReplyId, @NotEmpty String commentText, UserEntity userEntity,
            CommentOnAnswer commentOnAnswer, Date commnentDate, Time commentTime) {
        super();
        this.answerCommentReplyId = answerCommentReplyId;
        this.commentText = commentText;
        this.userEntity = userEntity;
        this.commentOnAnswer = commentOnAnswer;
        this.commnentDate = commnentDate;
        this.commentTime = commentTime;
    }

    public Long getAnswerCommentReplyId() {
        return answerCommentReplyId;
    }

    public void setAnswerCommentReplyId(Long answerCommentReplyId) {
        this.answerCommentReplyId = answerCommentReplyId;
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

    public CommentOnAnswer getCommentOnAnswer() {
        return commentOnAnswer;
    }

    public void setCommentOnAnswer(CommentOnAnswer commentOnAnswer) {
        this.commentOnAnswer = commentOnAnswer;
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
        return "AnswerCommentReply [answerCommentReplyId=" + answerCommentReplyId + ", commentText=" + commentText
                + ", userEntity=" + userEntity + ", commentOnAnswer=" + commentOnAnswer + ", commnentDate="
                + commnentDate + ", commentTime=" + commentTime + "]";
    }

}
