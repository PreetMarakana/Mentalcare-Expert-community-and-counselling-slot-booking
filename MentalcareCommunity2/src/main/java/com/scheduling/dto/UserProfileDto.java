package com.scheduling.dto;

import java.util.Set;

public class UserProfileDto {

    private Long userId;

    private String userName;

    private String profilePath;

    private Long noPost;

    private Long noFollower;

    private Long noFollowing;

    private Set<FeedsDto> feedsDtos;

    private boolean followingOrNot;
    
    
    public Set<FeedsDto> getFeedsDtos() {
        return feedsDtos;
    }

    public void setFeedsDtos(Set<FeedsDto> feedsDtos) {
        this.feedsDtos = feedsDtos;
    }

    public UserProfileDto(Long userId, String userName, String profilePath, Long noPost, Long noFollower,
            Long noFollowing, Set<FeedsDto> feedsDtos, boolean followingOrNot) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.profilePath = profilePath;
        this.noPost = noPost;
        this.noFollower = noFollower;
        this.noFollowing = noFollowing;
        this.feedsDtos = feedsDtos;
        this.followingOrNot = followingOrNot;
    }

    public UserProfileDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public Long getNoPost() {
        return noPost;
    }

    public void setNoPost(Long noPost) {
        this.noPost = noPost;
    }

    public Long getNoFollower() {
        return noFollower;
    }

    public void setNoFollower(Long noFollower) {
        this.noFollower = noFollower;
    }

    public Long getNoFollowing() {
        return noFollowing;
    }

    public void setNoFollowing(Long noFollowing) {
        this.noFollowing = noFollowing;
    }


    public boolean isFollowingOrNot() {
        return followingOrNot;
    }

    public void setFollowingOrNot(boolean followingOrNot) {
        this.followingOrNot = followingOrNot;
    }

}
