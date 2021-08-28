package com.scheduling.dto;

import org.springframework.web.multipart.MultipartFile;

public class ExpertDto {
    
    private long expertId;
    
    private String expertName;
    
    private String expertEmail;
    
    private String expertType;
    
    private long conversationCount;
    
    private long rating;

    private MultipartFile expertProfile;
    
    public ExpertDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    public ExpertDto(long expertId, String expertName, String expertEmail, String expertType, long conversationCount,
            long rating, MultipartFile expertProfile) {
        super();
        this.expertId = expertId;
        this.expertName = expertName;
        this.expertEmail = expertEmail;
        this.expertType = expertType;
        this.conversationCount = conversationCount;
        this.rating = rating;
        this.expertProfile = expertProfile;
    }

    public MultipartFile getExpertProfile() {
        return expertProfile;
    }

    public void setExpertProfile(MultipartFile expertProfile) {
        this.expertProfile = expertProfile;
    }


    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public String getExpertEmail() {
        return expertEmail;
    }

    public void setExpertEmail(String expertEmail) {
        this.expertEmail = expertEmail;
    }

    public String getExpertType() {
        return expertType;
    }

    public void setExpertType(String expertType) {
        this.expertType = expertType;
    }

    public long getConversationCount() {
        return conversationCount;
    }

    public void setConversationCount(long conversationCount) {
        this.conversationCount = conversationCount;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public long getExpertId() {
        return expertId;
    }

    public void setExpertId(long expertId) {
        this.expertId = expertId;
    }

    @Override
    public String toString() {
        return "ExpertDto [expertId=" + expertId + ", expertName=" + expertName + ", expertEmail=" + expertEmail
                + ", expertType=" + expertType + ", conversationCount=" + conversationCount + ", rating=" + rating
                + "]";
    }

}
