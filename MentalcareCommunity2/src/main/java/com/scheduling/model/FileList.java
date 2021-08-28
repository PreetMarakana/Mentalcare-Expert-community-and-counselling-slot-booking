package com.scheduling.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class FileList{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;
    
    private String path;
    
    @ManyToOne
    @JsonBackReference
    private Post post;

    public FileList() {
        super();
        // TODO Auto-generated constructor stub
    }

    public FileList(String path, Post post) {
        super();
        this.path = path;
        this.post = post;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "FileList [fileId=" + fileId + ", path=" + path + ", post=" + post + "]";
    }
}
