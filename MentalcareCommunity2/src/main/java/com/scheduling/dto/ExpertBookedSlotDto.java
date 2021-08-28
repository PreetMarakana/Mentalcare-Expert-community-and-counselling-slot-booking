package com.scheduling.dto;

import java.sql.Date;
import java.sql.Time;

public class ExpertBookedSlotDto {
    
    private Time startTime;
    
    private Time endTime;
    
    private Date slotDate;

    public ExpertBookedSlotDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ExpertBookedSlotDto(Time startTime, Time endTime, Date slotDate) {
        super();
        this.startTime = startTime;
        this.endTime = endTime;
        this.slotDate = slotDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Date getSlotDate() {
        return slotDate;
    }

    public void setSlotDate(Date slotDate) {
        this.slotDate = slotDate;
    }

    @Override
    public String toString() {
        return "ExpertBookedSlotDto [startTime=" + startTime + ", endTime=" + endTime + ", slotDate=" + slotDate + "]";
    }

}
