package com.scheduling.dto;

public class ChangePasswordDto {

    private String password;
    private String email;

    public ChangePasswordDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ChangePasswordDto(String password, String email) {
        super();
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
