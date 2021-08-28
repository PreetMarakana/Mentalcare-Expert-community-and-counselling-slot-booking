package com.scheduling.dto;

public class UserDto {
    
    private long userId;
    
    private String userName;
    
    private String userEmail;
    
    private String userPassword;
    
    private boolean userVerify;

    public UserDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public UserDto( String userName, String userEmail, String userPassword, boolean userVerify) {
        super();
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userVerify = userVerify;
    }
    
    public UserDto(String userEmail, boolean userVerify,long userId) {
        super();
        this.userEmail = userEmail;
        this.userVerify = userVerify;
        this.userId = userId;
    }
    
    public UserDto(String userName2, String userEmail2) {
        this.userEmail = userEmail2;
        this.userName = userName2;
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
    
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserDto [userName=" + userName + ", userEmail=" + userEmail + ", userPassword="
                + userPassword + ", userVerify=" + userVerify + "]";
    }
    
}
