package com.scheduling.dto;

public class FacebookLoginRequest {

    private String accessToken;

    public FacebookLoginRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

    public FacebookLoginRequest(String accessToken) {
        super();
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "FacebookLoginRequest [accessToken=" + accessToken + "]";
    }
    
}
