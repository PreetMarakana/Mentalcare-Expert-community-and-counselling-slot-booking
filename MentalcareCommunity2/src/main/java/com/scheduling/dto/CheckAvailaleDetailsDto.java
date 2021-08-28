package com.scheduling.dto;

public class CheckAvailaleDetailsDto {

    private String date;
    
    private long expertId;

    public CheckAvailaleDetailsDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CheckAvailaleDetailsDto(String date, long expertId) {
        super();
        this.date = date;
        this.expertId = expertId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getExpertId() {
        return expertId;
    }

    public void setExpertId(long expertId) {
        this.expertId = expertId;
    }

    @Override
    public String toString() {
        return "CheckAvailaleDetails [date=" + date + ", expertId=" + expertId + "]";
    }
    
    
}
