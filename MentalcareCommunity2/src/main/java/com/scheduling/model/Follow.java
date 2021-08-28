package com.scheduling.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long followId;
    
    @ManyToOne
    @JoinColumn(name = "following_from")
    @JsonBackReference
    private UserEntity fromUser;
    
    @ManyToOne
    @JoinColumn(name = "follower_to")
    @JsonBackReference
    private UserEntity toUser;

    private Date followDate;
    
    private Time followTime;
    
    public Follow() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Follow(long followId, UserEntity fromUser, UserEntity toUser) {
        super();
        this.followId = followId;
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    public long getFollowId() {
        return followId;
    }

    public void setFollowId(long followId) {
        this.followId = followId;
    }

    public UserEntity getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserEntity fromUser) {
        this.fromUser = fromUser;
    }

    public UserEntity getToUser() {
        return toUser;
    }

    public void setToUser(UserEntity toUser) {
        this.toUser = toUser;
    }
    
    public Date getFollowDate() {
        return followDate;
    }

    public void setFollowDate(Date followDate) {
        this.followDate = followDate;
    }

    public Time getFollowTime() {
        return followTime;
    }

    public void setFollowTime(Time followTime) {
        this.followTime = followTime;
    }

    @Override
    public String toString() {
        return "Follow [followId=" + followId + ", fromUser=" + fromUser + ", toUser=" + toUser + "]";
    }
    
}
