package com.scheduling.model;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;

    private String postCaption;

    @ManyToOne
    private UserEntity userEntity;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonManagedReference
    private List<FileList> fileLists;

    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonManagedReference
    private List<CommentOnPost> commentOnPosts;
    
    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonManagedReference
    private List<PostLike> postLikes;
    
    private Date postDate;
    
    private Time postTime;
    
    public Post() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Post(long postId,String postCaption, UserEntity userEntity, List<FileList> fileLists) {
        super();
        this.postId = postId;
        this.postCaption = postCaption;
        this.userEntity = userEntity;
        this.fileLists = fileLists;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getPostCaption() {
        return postCaption;
    }

    public void setPostCaption(String postCaption) {
        this.postCaption = postCaption;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<FileList> getFileLists() {
        return fileLists;
    }

    public void setFileLists(List<FileList> fileLists) {
        this.fileLists = fileLists;
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

    public List<CommentOnPost> getCommentOnPosts() {
        return commentOnPosts;
    }

    public void setCommentOnPosts(List<CommentOnPost> commentOnPosts) {
        this.commentOnPosts = commentOnPosts;
    }

    public List<PostLike> getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(List<PostLike> postLikes) {
        this.postLikes = postLikes;
    }

    @Override
    public String toString() {
        return "Post [postId=" + postId + ", postCaption=" + postCaption + ", userEntity=" + userEntity + ", fileLists="
                + fileLists + ", postDate=" + postDate + ", postTime=" + postTime + "]";
    }
}
