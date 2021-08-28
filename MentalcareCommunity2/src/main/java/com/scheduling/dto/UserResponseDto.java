package com.scheduling.dto;

public class UserResponseDto {

    private long userId;
    
    private String username;
    
    private String profilePath;

    private boolean followOrNot;
    
    public UserResponseDto() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public UserResponseDto(long userId, String username, String profilePath, boolean followOrNot) {
        super();
        this.userId = userId;
        this.username = username;
        this.profilePath = profilePath;
        this.followOrNot = followOrNot;
    }

    public UserResponseDto(long userId, String username, String profilePath) {
        super();
        this.userId = userId;
        this.username = username;
        this.profilePath = profilePath;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public boolean isFollowOrNot() {
        return followOrNot;
    }

    public void setFollowOrNot(boolean followOrNot) {
        this.followOrNot = followOrNot;
    }

    @Override
    public String toString() {
        return "UserResponseDto [userId=" + userId + ", username=" + username + ", profilePath=" + profilePath + "]";
    }
    
}
