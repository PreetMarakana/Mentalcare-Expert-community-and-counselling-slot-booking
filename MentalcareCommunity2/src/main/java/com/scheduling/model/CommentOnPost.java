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
public class CommentOnPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentOnPostId;

    @NotEmpty
    private String comment;

    @ManyToOne
    @JsonManagedReference
    private UserEntity userEntity;

    @ManyToOne
    @JsonBackReference
    private Post post;

    @OneToMany(mappedBy = "CommentOnPost",fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonManagedReference
    private List<PostCommentReply> commentReplies;
    
    @OneToMany(mappedBy = "commentOnPost",fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonManagedReference
    private List<LikePostComment> likePostComments;
    
    
    private Date commnentDate;

    private Time commentTime;

    public CommentOnPost() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CommentOnPost(long commentOnPostId, @NotEmpty String comment, UserEntity userEntity, Post post,
            Date commnentDate, Time commentTime) {
        super();
        this.commentOnPostId = commentOnPostId;
        this.comment = comment;
        this.userEntity = userEntity;
        this.post = post;
        this.commnentDate = commnentDate;
        this.commentTime = commentTime;
    }

    public long getCommentOnPostId() {
        return commentOnPostId;
    }

    public void setCommentOnPostId(long commentOnPostId) {
        this.commentOnPostId = commentOnPostId;
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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
    
    public List<PostCommentReply> getCommentReplies() {
        return commentReplies;
    }

    public void setCommentReplies(List<PostCommentReply> commentReplies) {
        this.commentReplies = commentReplies;
    }
    
    public List<LikePostComment> getLikePostComments() {
        return likePostComments;
    }

    public void setLikePostComments(List<LikePostComment> likePostComments) {
        this.likePostComments = likePostComments;
    }

    @Override
    public String toString() {
        return "CommentOnPost [commentOnPostId=" + commentOnPostId + ", comment=" + comment + ", userEntity="
                + userEntity + ", post=" + post + ", commnentDate=" + commnentDate + ", commentTime=" + commentTime
                + "]";
    }
    
    
}
