package com.scheduling.dto;

public class SlotDetailsDto {

    private String fromDate;

    private String toDate;

    private String startTime;

    private String endTime;

    private long expert;

    public SlotDetailsDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public SlotDetailsDto(String fromDate, String toDate, String startTime, String endTime, long expert) {
        super();
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.expert = expert;
    }

    public SlotDetailsDto(String fromDate, String toDate, String startTime, String endTime) {
        super();
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public long getExpert() {
        return expert;
    }

    public void setExpert(long expert) {
        this.expert = expert;
    }

    @Override
    public String toString() {
        return "SlotDetailsDto [fromDate=" + fromDate + ", toDate=" + toDate + ", startTime=" + startTime + ", endTime="
                + endTime + ", expert=" + expert + "]";
    }

}
