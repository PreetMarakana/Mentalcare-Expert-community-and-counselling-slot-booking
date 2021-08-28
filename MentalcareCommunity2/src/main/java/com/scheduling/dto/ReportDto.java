package com.scheduling.dto;

public class ReportDto {

    private String type;
    
    private String user;
    
    private String id;

    public ReportDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ReportDto(String type, String user, String id) {
        super();
        this.type = type;
        this.user = user;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ReportDto [type=" + type + ", user=" + user + ", id=" + id + "]";
    }
    
}
