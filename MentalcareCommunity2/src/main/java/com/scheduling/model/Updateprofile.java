package com.scheduling.model;

import org.springframework.web.multipart.MultipartFile;

public class Updateprofile {

    private MultipartFile profilePath;

    public Updateprofile() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Updateprofile(MultipartFile profilePath) {
        super();
        this.profilePath = profilePath;
    }

    public MultipartFile getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(MultipartFile profilePath) {
        this.profilePath = profilePath;
    }
    
    
}
