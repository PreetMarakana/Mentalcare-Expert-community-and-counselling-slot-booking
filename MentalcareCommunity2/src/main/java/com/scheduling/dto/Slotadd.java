package com.scheduling.dto;

public class Slotadd {

    private String startTime;
    
    private String endTime;
    
    private String startDate;

    private String endDate;
    
    private String doctorId;
    
    public Slotadd() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Slotadd(String startTIme, String endTime, String startDate, String endDate, String doctorId) {
        super();
        this.startTime = startTIme;
        this.endTime = endTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.doctorId = doctorId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTIme) {
        this.startTime = startTIme;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return "Slotadd [startTIme=" + startTime + ", endTime=" + endTime + ", startDate=" + startDate + ", endDate="
                + endDate + ", doctorId=" + doctorId + "]";
    }
    
}
