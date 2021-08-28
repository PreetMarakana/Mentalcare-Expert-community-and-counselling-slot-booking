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
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postLikeId;
    
    @ManyToOne
    @JsonBackReference
    private UserEntity userEntity;
    
    @ManyToOne
    @JsonBackReference
    private Post post;
    
    private Date likeDate;
    
    private Time likeTime;

    public PostLike() {
        super();
        // TODO Auto-generated constructor stub
    }

    public PostLike(long postLikeId, UserEntity userEntity, Post post, Date likeDate, Time likeTime) {
        super();
        this.postLikeId = postLikeId;
        this.userEntity = userEntity;
        this.post = post;
        this.likeDate = likeDate;
        this.likeTime = likeTime;
    }

    public long getPostLikeId() {
        return postLikeId;
    }

    public void setPostLikeId(long postLikeId) {
        this.postLikeId = postLikeId;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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
        return "PostLike [postLikeId=" + postLikeId + ", userEntity=" + userEntity + ", post=" + post + ", likeDate="
                + likeDate + ", likeTime=" + likeTime + "]";
    }
}
