package com.scheduling.dto;

import javax.validation.constraints.NotEmpty;

public class CommentPostRepliesRequestDto {

  private Long replyId;
    
    @NotEmpty
    private String comment;

    public CommentPostRepliesRequestDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CommentPostRepliesRequestDto(Long replyId, @NotEmpty String comment) {
        super();
        this.replyId = replyId;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "CommentPostRepliesRequestDto [replyId=" + replyId + ", comment=" + comment + "]";
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

    
}
