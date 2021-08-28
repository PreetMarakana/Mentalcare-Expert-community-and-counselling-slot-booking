package com.scheduling.dto;

public class ExpertResponseDto {

    private long expertId;
    
    private String expertName;
    
    private String expertEmail;
    
    private String expertType;
    
    private long conversationCount;
    
    private long rating;
    
    private String expertPath;

    public ExpertResponseDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ExpertResponseDto(long expertId, String expertName, String expertEmail, String expertType,
            long conversationCount, long rating, String expertPath) {
        super();
        this.expertId = expertId;
        this.expertName = expertName;
        this.expertEmail = expertEmail;
        this.expertType = expertType;
        this.conversationCount = conversationCount;
        this.rating = rating;
        this.expertPath = expertPath;
    }

    public long getExpertId() {
        return expertId;
    }

    public void setExpertId(long expertId) {
        this.expertId = expertId;
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

    public String getExpertPath() {
        return expertPath;
    }

    public void setExpertPath(String expertPath) {
        this.expertPath = expertPath;
    }

    @Override
    public String toString() {
        return "ExpertResponseDto [expertId=" + expertId + ", expertName=" + expertName + ", expertEmail=" + expertEmail
                + ", expertType=" + expertType + ", conversationCount=" + conversationCount + ", rating=" + rating
                + ", expertPath=" + expertPath + "]";
    }
    
}
