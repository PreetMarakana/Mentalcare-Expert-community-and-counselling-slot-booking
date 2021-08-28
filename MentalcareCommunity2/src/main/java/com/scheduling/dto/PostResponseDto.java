package com.scheduling.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class PostResponseDto {

    private long postId;
    
    private String caption;
    
    private List<String> files;
    
    private UserResponseDto userResponseDto;

    private Long noLikes;
    
    private Long noComments;
    
    private Date postDate;
    
    private Time postTime;
    
    private boolean liked;
    
    public PostResponseDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public PostResponseDto(long postId, String caption, List<String> files, UserResponseDto userResponseDto) {
        super();
        this.postId = postId;
        this.caption = caption;
        this.files = files;
        this.userResponseDto = userResponseDto;
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

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public UserResponseDto getUserResponseDto() {
        return userResponseDto;
    }

    public void setUserResponseDto(UserResponseDto userResponseDto) {
        this.userResponseDto = userResponseDto;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Time getPostTime() {
        return postTime;
    }

    public void setPostTime(Time postTime) {
        this.postTime = postTime;
    }
    
    public Long getNoLikes() {
        return noLikes;
    }

    public void setNoLikes(Long noLikes) {
        this.noLikes = noLikes;
    }

    public Long getNoComments() {
        return noComments;
    }

    public void setNoComments(Long noComments) {
        this.noComments = noComments;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public PostResponseDto(long postId, String caption, List<String> files, UserResponseDto userResponseDto,
            Date postDate, Time postTime) {
        super();
        this.postId = postId;
        this.caption = caption;
        this.files = files;
        this.userResponseDto = userResponseDto;
        this.postDate = postDate;
        this.postTime = postTime;
    }

    @Override
    public String toString() {
        return "PostResponseDto [postId=" + postId + ", caption=" + caption + ", files=" + files + ", userResponseDto="
                + userResponseDto + ", postDate=" + postDate + ", postTime=" + postTime + "]";
    }

}
