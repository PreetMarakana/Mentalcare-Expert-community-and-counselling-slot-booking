package com.scheduling.dto;

import java.sql.Date;
import java.sql.Time;

public class BookingDto {

    private long bookingId;
    
    private UserDto userDto;
    
    private ExpertResponseDto expertDto;
    
    private SlotDetailsDtoUser slotDetailsDto;
    
    private Time bookingTime;
    
    private Date bookingDate;
    
    private String orederId;
    
    private String paymentId;
    
    private String amount;
    
    private String bookingStatus;

    public BookingDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public BookingDto(long bookingId, UserDto userDto, ExpertResponseDto expertDto, SlotDetailsDtoUser slotDetailsDto,
            Time bookingTime, Date bookingDate, String bookingStatus) {
        super();
        this.bookingId = bookingId;
        this.userDto = userDto;
        this.expertDto = expertDto;
        this.slotDetailsDto = slotDetailsDto;
        this.bookingTime = bookingTime;
        this.bookingDate = bookingDate;
        this.bookingStatus = bookingStatus;
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public ExpertResponseDto getExpertDto() {
        return expertDto;
    }

    public void setExpertDto(ExpertResponseDto expertDto) {
        this.expertDto = expertDto;
    }

    public SlotDetailsDtoUser getSlotDetailsDtoUser() {
        return slotDetailsDto;
    }

    public void setSlotDetailsDtoUser(SlotDetailsDtoUser slotDetailsDto) {
        this.slotDetailsDto = slotDetailsDto;
    }

    public Time getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Time bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public SlotDetailsDtoUser getSlotDetailsDto() {
        return slotDetailsDto;
    }

    public void setSlotDetailsDto(SlotDetailsDtoUser slotDetailsDto) {
        this.slotDetailsDto = slotDetailsDto;
    }

    public String getOrederId() {
        return orederId;
    }

    public void setOrederId(String orederId) {
        this.orederId = orederId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BookingDto [bookingId=" + bookingId + ", userDto=" + userDto + ", expertDto=" + expertDto
                + ", slotDetailsDto=" + slotDetailsDto + ", bookingTime=" + bookingTime + ", bookingDate=" + bookingDate
                + ", orederId=" + orederId + ", paymentId=" + paymentId + ", amount=" + amount + ", bookingStatus="
                + bookingStatus + "]";
    }
    
}