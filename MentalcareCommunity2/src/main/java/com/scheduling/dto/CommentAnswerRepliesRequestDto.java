package com.scheduling.dto;

import javax.validation.constraints.NotEmpty;

public class CommentAnswerRepliesRequestDto {

    private Long replyId;
    
    @NotEmpty
    private String comment;

    public CommentAnswerRepliesRequestDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CommentAnswerRepliesRequestDto(Long replyId, @NotEmpty String comment) {
        super();
        this.replyId = replyId;
        this.comment = comment;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "CommentAnswerRepliesRequestDto [replyId=" + replyId + ", comment=" + comment + "]";
    }
    
    
}
