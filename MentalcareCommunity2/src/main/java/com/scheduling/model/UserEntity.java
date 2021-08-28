package com.scheduling.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @NotEmpty
    private String userName;

    @NotEmpty
    private String userEmail;

    @JsonIgnore
    @NotEmpty
    private String userPassword;

    @JsonIgnore
    private String mobileToken;
    
    private String profilePath;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SlotBooking> slotBooking;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Question> questions;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Answer> answers;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Post> posts;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<CommentOnAnswer> commentOnAnswer;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<CommentOnPost> commentOnPost;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<LikeAnswer> likeAnswers;
    
    @OneToMany(mappedBy = "userEntity",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonBackReference
    private List<PostLike> postLikes;
    
    @OneToMany(mappedBy = "userEntity" , cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonBackReference
    private List<LikePostComment> likePostComment;
    
    @OneToMany(mappedBy = "userEntity" , cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonBackReference
    private List<LikeAnswerComment> likeAnswerComment;
    
    @OneToMany(mappedBy = "toUser",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Follow> followers;
    
    @OneToMany(mappedBy = "fromUser",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Follow> following;

    @OneToMany(mappedBy = "receiver",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Notification> notifications;
    
    @OneToMany(mappedBy = "sender",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Notification> notificationsender;
    
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean userVerify;

    @JsonIgnore
    private String faceBookId;
    
    
    public UserEntity() {
        super();
        // TODO Auto-generated constructor stub
    }

    public UserEntity(long userId, @NotEmpty String userName, @NotEmpty String userEmail, @NotEmpty String userPassword,
            boolean userVerify) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userVerify = userVerify;
    }

    
    public String getMobileToken() {
        return mobileToken;
    }

    public void setMobileToken(String mobileToken) {
        this.mobileToken = mobileToken;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isUserVerify() {
        return userVerify;
    }

    public void setUserVerify(boolean userVerify) {
        this.userVerify = userVerify;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public List<SlotBooking> getSlotBooking() {
        return slotBooking;
    }

    public void setSlotBooking(List<SlotBooking> slotBooking) {
        this.slotBooking = slotBooking;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    
    public List<CommentOnAnswer> getCommentOnAnswer() {
        return commentOnAnswer;
    }

    public void setCommentOnAnswer(List<CommentOnAnswer> commentOnAnswer) {
        this.commentOnAnswer = commentOnAnswer;
    }

    public List<CommentOnPost> getCommentOnPost() {
        return commentOnPost;
    }

    public void setCommentOnPost(List<CommentOnPost> commentOnPost) {
        this.commentOnPost = commentOnPost;
    }

    public List<LikeAnswer> getLikeAnswers() {
        return likeAnswers;
    }

    public void setLikeAnswers(List<LikeAnswer> likeAnswers) {
        this.likeAnswers = likeAnswers;
    }

    public List<PostLike> getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(List<PostLike> postLikes) {
        this.postLikes = postLikes;
    }

    public List<Follow> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Follow> followers) {
        this.followers = followers;
    }

    public List<Follow> getFollowing() {
        return following;
    }

    public void setFollowing(List<Follow> following) {
        this.following = following;
    }

    public List<LikePostComment> getLikePostComment() {
        return likePostComment;
    }

    public void setLikePostComment(List<LikePostComment> likePostComment) {
        this.likePostComment = likePostComment;
    }

    public List<LikeAnswerComment> getLikeAnswerComment() {
        return likeAnswerComment;
    }

    public void setLikeAnswerComment(List<LikeAnswerComment> likeAnswerComment) {
        this.likeAnswerComment = likeAnswerComment;
    }

    public String getFaceBookId() {
        return faceBookId;
    }

    public void setFaceBookId(String faceBookId) {
        this.faceBookId = faceBookId;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    @Override
    public String toString() {
        return "UserEntity [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail
                + ", userPassword=" + userPassword + ", userVerify=" + userVerify + "]";
    }

}
