package com.scheduling.dto;

import javax.validation.constraints.NotEmpty;

public class MobileTokenDto {

    @NotEmpty
    private String token;

    public MobileTokenDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public MobileTokenDto(String token) {
        super();
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    
}
