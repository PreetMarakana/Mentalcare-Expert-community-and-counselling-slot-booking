package com.scheduling.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AnswerDto {

    @JsonIgnore
    private long answerId;
    
    private String answer;

    public AnswerDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public AnswerDto(String answer) {
        super();
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public AnswerDto(long answerId, String answer) {
        super();
        this.answerId = answerId;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "AnswerDto [answer=" + answer + "]";
    }
    
    
}