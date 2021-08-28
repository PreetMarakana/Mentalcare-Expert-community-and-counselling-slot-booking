package com.scheduling.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class SlotBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;
    
    @OneToOne(fetch = FetchType.LAZY)
    private SlotAvailable slotAvailable;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Expert expert;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity userEntity;
    
    private Time bookingTime;
    
    private Date bookingDate;
    
    private String orederId;
    
    private String paymentId;
    
    private String amount;
    
    private String userEmail;
    
    private String userMobile;
    
    private String category;
    
    @NotEmpty
    private String bookingStatus;
    
    public SlotBooking() {
        super();
        // TODO Auto-generated constructor stub
    }

    public SlotBooking(long bookingId, @NotEmpty SlotAvailable slotAvailable, @NotEmpty Expert expert,
            @NotEmpty UserEntity userEntity, Time bookingTime, Date bookingDate, @NotEmpty String bookingStatus) {
        super();
        this.bookingId = bookingId;
        this.slotAvailable = slotAvailable;
        this.expert = expert;
        this.userEntity = userEntity;
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

    public SlotAvailable getSlotAvailable() {
        return slotAvailable;
    }

    public void setSlotAvailable(SlotAvailable slotAvailable) {
        this.slotAvailable = slotAvailable;
    }

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "SlotBooking [bookingId=" + bookingId + ", slotAvailable=" + slotAvailable + ", expert=" + expert
                + ", userEntity=" + userEntity + ", bookingTime=" + bookingTime + ", bookingDate=" + bookingDate
                + ", orederId=" + orederId + ", paymentId=" + paymentId + ", amount=" + amount + ", userEmail="
                + userEmail + ", userMobile=" + userMobile + ", category=" + category + ", bookingStatus="
                + bookingStatus + "]";
    }
    
    

}
