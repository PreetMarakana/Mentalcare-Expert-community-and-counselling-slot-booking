package com.scheduling.dto;

public class BookingRequestDto {

    private long slotId;
    
    private String emailId;
    
    private String phoneNumber;
    
    private String category;

    public BookingRequestDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public BookingRequestDto(long slotId, String emailId, String phoneNumber, String category) {
        super();
        this.slotId = slotId;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.category = category;
    }

    public long getSlotId() {
        return slotId;
    }

    public void setSlotId(long slotId) {
        this.slotId = slotId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "BookingRequestDto [slotId=" + slotId + ", emailId=" + emailId + ", phoneNumber=" + phoneNumber
                + ", category=" + category + "]";
    }

    
}
