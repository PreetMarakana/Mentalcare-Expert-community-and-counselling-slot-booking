package com.scheduling.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Report {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reportId;

    private String type;
    
    private String user;
    
    private String postId;

    public Report() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Report(long reportId, String type, String user, String postId) {
        super();
        this.reportId = reportId;
        this.type = type;
        this.user = user;
        this.postId = postId;
    }

    public long getReportId() {
        return reportId;
    }

    public void setReportId(long reportId) {
        this.reportId = reportId;
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

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "Report [reportId=" + reportId + ", type=" + type + ", user=" + user + ", postId=" + postId + "]";
    }
    
    
     
}
