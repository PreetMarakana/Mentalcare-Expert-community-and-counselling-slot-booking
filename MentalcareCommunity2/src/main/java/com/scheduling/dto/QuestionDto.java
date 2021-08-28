package com.scheduling.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class QuestionDto {

//    @JsonIgnore
    private long questionId;
    
    private String question;

    public QuestionDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public QuestionDto(long questionId, String question) {
        super();
        this.questionId = questionId;
        this.question = question;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "QuestionDto [questionId=" + questionId + ", question=" + question + "]";
    }

}
