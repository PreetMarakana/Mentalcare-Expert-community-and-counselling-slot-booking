package com.scheduling.dto;

import javax.validation.constraints.NotEmpty;

public class UserDetailsUpdate {

    @NotEmpty
    private String username;
    

    public UserDetailsUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

    public UserDetailsUpdate(@NotEmpty String username) {
        super();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserDetailsUpdate [username=" + username + "]";
    }

}
