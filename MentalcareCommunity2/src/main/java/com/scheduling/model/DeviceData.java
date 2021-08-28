package com.scheduling.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DeviceData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceId;
    
    private Long userId;
    
    private String deviceDetails;
    
    private String location;
    
    private Date lastLoggedIn;

    public DeviceData() {
        super();
        // TODO Auto-generated constructor stub
    }

    public DeviceData(Long deviceId, Long userId, String deviceDetails, String location, Date lastLoggedIn) {
        super();
        this.deviceId = deviceId;
        this.userId = userId;
        this.deviceDetails = deviceDetails;
        this.location = location;
        this.lastLoggedIn = lastLoggedIn;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDeviceDetails() {
        return deviceDetails;
    }

    public void setDeviceDetails(String deviceDetails) {
        this.deviceDetails = deviceDetails;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(Date lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    @Override
    public String toString() {
        return "DeviceData [deviceId=" + deviceId + ", userId=" + userId + ", deviceDetails=" + deviceDetails
                + ", location=" + location + ", lastLoggedIn=" + lastLoggedIn + "]";
    }
    
}
