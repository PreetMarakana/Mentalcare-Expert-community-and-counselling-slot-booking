package com.scheduling.model;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class CommentOnAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentOnAnswerId;

    @NotEmpty
    private String comment;

    @ManyToOne
    @JsonManagedReference
    private UserEntity userEntity;

    @OneToMany(mappedBy = "commentOnAnswer",fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonManagedReference
    private List<AnswerCommentReply> postCommentReplies;
    
    @ManyToOne
    @JsonBackReference
    private Answer answer;

    @OneToMany(mappedBy = "commentOnAnswer",fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonManagedReference
    private List<LikeAnswerComment> answerComments;
    
    private Date commnentDate;

    private Time commentTime;

    public CommentOnAnswer() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CommentOnAnswer(long commentOnAnswerId, @NotEmpty String comment, UserEntity userEntity, Answer answer) {
        super();
        this.commentOnAnswerId = commentOnAnswerId;
        this.comment = comment;
        this.userEntity = userEntity;
        this.answer = answer;
    }

    public long getCommentOnAnswerId() {
        return commentOnAnswerId;
    }

    public void setCommentOnAnswerId(long commentOnAnswerId) {
        this.commentOnAnswerId = commentOnAnswerId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
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

    public List<AnswerCommentReply> getPostCommentReplies() {
        return postCommentReplies;
    }

    public void setPostCommentReplies(List<AnswerCommentReply> postCommentReplies) {
        this.postCommentReplies = postCommentReplies;
    }

    public List<LikeAnswerComment> getAnswerComments() {
        return answerComments;
    }

    public void setAnswerComments(List<LikeAnswerComment> answerComments) {
        this.answerComments = answerComments;
    }

    @Override
    public String toString() {
        return "CommentOnAnswer [commentOnAnswerId=" + commentOnAnswerId + ", comment=" + comment + ", userEntity="
                + userEntity + ", answer=" + answer + "]";
    }

}
