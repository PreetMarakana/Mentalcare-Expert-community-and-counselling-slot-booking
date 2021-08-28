package com.scheduling.dto;

public class AnswerCommentDto {
    
    private String comment;

    public AnswerCommentDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public AnswerCommentDto(String comment) {
        super();
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "AnswerCommentDto [comment=" + comment + "]";
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
    
}
