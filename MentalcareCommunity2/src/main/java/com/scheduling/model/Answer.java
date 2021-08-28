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

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerId;
    
    @NotEmpty
    private String userAnswer;
    
    @ManyToOne
    private UserEntity userEntity;
    
    @ManyToOne
    private Question question;

    @OneToMany(mappedBy = "answer",fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonManagedReference
    private List<CommentOnAnswer> commentOnAnswers;
    
    @OneToMany(mappedBy = "answer",fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonManagedReference
    private List<LikeAnswer> likeAnswers;
    
    private Time answerTime;
    
    private Date answerDate;
    
    public Time getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Time answerTime) {
        this.answerTime = answerTime;
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer [answerId=" + answerId + ", userAnswer=" + userAnswer + ", userEntity=" + userEntity
                + ", question=" + question + "]";
    }

    public Answer() {
        super();
        // TODO Auto-generated constructor stub
    }

    public List<CommentOnAnswer> getCommentOnAnswers() {
        return commentOnAnswers;
    }

    public void setCommentOnAnswers(List<CommentOnAnswer> commentOnAnswers) {
        this.commentOnAnswers = commentOnAnswers;
    }

    public List<LikeAnswer> getLikeAnswers() {
        return likeAnswers;
    }

    public void setLikeAnswers(List<LikeAnswer> likeAnswers) {
        this.likeAnswers = likeAnswers;
    }
    
}
