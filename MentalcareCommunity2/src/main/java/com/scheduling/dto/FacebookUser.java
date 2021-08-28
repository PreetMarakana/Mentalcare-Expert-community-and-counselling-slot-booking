package com.scheduling.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FacebookUser {

    private String id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String email;
    private FacebookPicture picture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public FacebookPicture getPicture() {
        return picture;
    }

    public void setPicture(FacebookPicture picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "FacebookUser [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", picture=" + picture + "]";
    }

    public FacebookUser() {
        super();
        // TODO Auto-generated constructor stub
    }

    public FacebookUser(String id, String firstName, String lastName, String email, FacebookPicture picture) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.picture = picture;
    }

}
