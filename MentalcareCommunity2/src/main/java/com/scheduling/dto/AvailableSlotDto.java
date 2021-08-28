package com.scheduling.dto;

import java.sql.Date;
import java.sql.Time;

public class AvailableSlotDto {

    private long slotId;
    
    private Time startTime;
    
    private Time endTime;
    
    private Date slotDate;
    
    private boolean available;

    public AvailableSlotDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public AvailableSlotDto(long slotId, Time startTime, Time endTime, Date slotDate,boolean available) {
        super();
        this.slotId = slotId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.slotDate = slotDate;
        this.available = available;
    }

    public AvailableSlotDto(Time startTime, Time endTime, Date slotDate) {
        super();
        this.startTime = startTime;
        this.endTime = endTime;
        this.slotDate = slotDate;
    }

    public long getSlotId() {
        return slotId;
    }

    public void setSlotId(long slotId) {
        this.slotId = slotId;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "AvailableSlotDto [slotId=" + slotId + ", startTime=" + startTime + ", endTime=" + endTime
                + ", slotDate=" + slotDate + ", available=" + available + "]";
    }

}
