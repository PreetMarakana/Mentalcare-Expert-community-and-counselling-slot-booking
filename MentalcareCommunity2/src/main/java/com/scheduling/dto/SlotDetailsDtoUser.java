package com.scheduling.dto;

import java.sql.Date;
import java.sql.Time;

public class SlotDetailsDtoUser {

    
    private long slotId;
    
    private Date slotDate;
    
    private Time startTime;
    
    private Time endTime;

    public SlotDetailsDtoUser(long slotId, Date slotDate, Time startTime, Time endTime) {
        super();
        this.slotId = slotId;
        this.slotDate = slotDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public SlotDetailsDtoUser() {
        super();
        // TODO Auto-generated constructor stub
    }

    public long getSlotId() {
        return slotId;
    }

    public void setSlotId(long slotId) {
        this.slotId = slotId;
    }

    public Date getSlotDate() {
        return slotDate;
    }

    public void setSlotDate(Date slotDate) {
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

    @Override
    public String toString() {
        return "SlotDetailsDtoUser [slotId=" + slotId + ", slotDate=" + slotDate + ", startTime=" + startTime
                + ", endTime=" + endTime + "]";
    }
    
}
