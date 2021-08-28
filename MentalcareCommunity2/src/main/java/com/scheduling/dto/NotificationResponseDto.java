package com.scheduling.dto;

public class NotificationResponseDto {

    UserResponseDto receiver;
    UserResponseDto sender;
    String date;
    String time;
    String message;
    public NotificationResponseDto() {
        super();
        // TODO Auto-generated constructor stub
    }
    public NotificationResponseDto(UserResponseDto receiver, UserResponseDto sender, String date, String time,
            String message) {
        super();
        this.receiver = receiver;
        this.sender = sender;
        this.date = date;
        this.time = time;
        this.message = message;
    }
    public UserResponseDto getReceiver() {
        return receiver;
    }
    public void setReceiver(UserResponseDto receiver) {
        this.receiver = receiver;
    }
    public UserResponseDto getSender() {
        return sender;
    }
    public void setSender(UserResponseDto sender) {
        this.sender = sender;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return "NotificationResponseDto [receiver=" + receiver + ", sender=" + sender + ", date=" + date + ", time="
                + time + ", message=" + message + "]";
    }
}
