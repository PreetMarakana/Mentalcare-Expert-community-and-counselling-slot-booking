package com.scheduling.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class PostDto {

    private long postId;
    
    private String caption;
    
    private List<MultipartFile> files;

    public PostDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public PostDto(long postId, String caption, List<MultipartFile> files) {
        super();
        this.postId = postId;
        this.caption = caption;
        this.files = files;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "PostDto [postId=" + postId + ", caption=" + caption + ", files=" + files + "]";
    }
    
}
