package com.scheduling.dto;

public class CommentOnPostDto {

    private String comment;

    public CommentOnPostDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CommentOnPostDto(String comment) {
        super();
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "CommentOnPostDto [comment=" + comment + "]";
    }
    
}
