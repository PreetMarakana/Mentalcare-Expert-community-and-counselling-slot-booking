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
public class LikeAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long likeAnswerId;
    
    @ManyToOne
    @JsonBackReference
    private UserEntity userEntity;
    
    @ManyToOne
    @JsonBackReference
    private Answer answer;
    
    private Date likeDate;
    
    private Time likeTime;

    public LikeAnswer() {
        super();
        // TODO Auto-generated constructor stub
    }

    public LikeAnswer(long likeAnswerId, UserEntity userEntity, Answer answer, Date likeDate, Time likeTime) {
        super();
        this.likeAnswerId = likeAnswerId;
        this.userEntity = userEntity;
        this.answer = answer;
        this.likeDate = likeDate;
        this.likeTime = likeTime;
    }

    public long getLikeAnswerId() {
        return likeAnswerId;
    }

    public void setLikeAnswerId(long likeAnswerId) {
        this.likeAnswerId = likeAnswerId;
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

    public Date getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Date likeDate) {
        this.likeDate = likeDate;
    }

    public Time getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(Time likeTime) {
        this.likeTime = likeTime;
    }

    @Override
    public String toString() {
        return "LikeAnswer [likeAnswerId=" + likeAnswerId + ", userEntity=" + userEntity + ", answer=" + answer
                + ", likeDate=" + likeDate + ", likeTime=" + likeTime + "]";
    }
    
}
