package com.scheduling.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;
    
    @ManyToOne
    @JsonManagedReference
    private UserEntity receiver;
 
    @ManyToOne
    @JsonManagedReference
    private UserEntity sender;
    
    @NotEmpty
    private String title;
    
    @NotEmpty
    private String message;
    
    private Date notificationDate;
    
    private Time notificationTime;

    public Notification() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Notification(Long notificationId, UserEntity receiver, UserEntity sender, @NotEmpty String title,
            @NotEmpty String message) {
        super();
        this.notificationId = notificationId;
        this.receiver = receiver;
        this.sender = sender;
        this.title = title;
        this.message = message;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public UserEntity getReceiver() {
        return receiver;
    }

    public void setReceiver(UserEntity receiver) {
        this.receiver = receiver;
    }

    public UserEntity getSender() {
        return sender;
    }

    public void setSender(UserEntity sender) {
        this.sender = sender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = notificationDate;
    }

    public Time getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(Time notificationTime) {
        this.notificationTime = notificationTime;
    }
    
    
}
