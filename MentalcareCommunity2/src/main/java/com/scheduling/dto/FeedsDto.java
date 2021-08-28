package com.scheduling.dto;

import java.sql.Date;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FeedsDto implements Comparable<FeedsDto> {
    
    private PostResponseDto postResponseDto;
    
    private QuestionResponseDto questionResponseDto;
    
    private AnswerResponseDto answerResponseDto;
    
    private int flag;
    
    @JsonIgnore
    private Time time;
    
    @JsonIgnore
    private Date date;
    
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PostResponseDto getPostResponseDto() {
        return postResponseDto;
    }

    public void setPostResponseDto(PostResponseDto postResponseDto) {
        this.postResponseDto = postResponseDto;
    }

    public QuestionResponseDto getQuestionResponseDto() {
        return questionResponseDto;
    }

    public void setQuestionResponseDto(QuestionResponseDto questionResponseDto) {
        this.questionResponseDto = questionResponseDto;
    }

    public AnswerResponseDto getAnswerResponseDto() {
        return answerResponseDto;
    }

    public void setAnswerResponseDto(AnswerResponseDto answerResponseDto) {
        this.answerResponseDto = answerResponseDto;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "FeedsDto [postResponseDto=" + postResponseDto + ", questionResponseDto=" + questionResponseDto
                + ", answerResponseDto=" + answerResponseDto + ", flag=" + flag + "]";
    }

    public FeedsDto() {
        super();
        // TODO Auto-generated constructor stub
    }

    public FeedsDto(PostResponseDto postResponseDto, QuestionResponseDto questionResponseDto,
            AnswerResponseDto answerResponseDto, int flag) {
        super();
        this.postResponseDto = postResponseDto;
        this.questionResponseDto = questionResponseDto;
        this.answerResponseDto = answerResponseDto;
        this.flag = flag;
    }

    @Override
    public int compareTo(FeedsDto o) {
//        if (o.getFlag()==1 && this.getFlag()==1) {
//            if (o.getQuestionResponseDto().getQuestionDate().after(this.getQuestionResponseDto().getQuestionDate())) {
//                return 1;
//            }else if (o.getQuestionResponseDto().getQuestionDate().before(this.getQuestionResponseDto().getQuestionDate())) {
//                return -1;
//            }else {
//               return o.getQuestionResponseDto().getQuestionTime().before(this.getQuestionResponseDto().getQuestionTime())?-1:1; 
//            }
//        }else if (o.getFlag()==3 && this.getFlag()==3) {
//            if (o.getAnswerResponseDto().getAnswerDate().after(this.getAnswerResponseDto().getAnswerDate())) {
//                return 1;
//            }else if (o.getAnswerResponseDto().getAnswerDate().before(this.getAnswerResponseDto().getAnswerDate())) {
//                return -1;
//            }else {
//               return o.getAnswerResponseDto().getAnswerTime().before(this.getAnswerResponseDto().getAnswerTime())?-1:1; 
//            }
//        }else if(o.getFlag()==2 && this.getFlag()==2){
//            if (o.getPostResponseDto().getPostDate().after(this.getPostResponseDto().getPostDate())) {
//                return 1;
//            }else if (o.getPostResponseDto().getPostDate().before(this.getPostResponseDto().getPostDate())) {
//                return -1;
//            }else {
//               return o.getPostResponseDto().getPostTime().before(this.getPostResponseDto().getPostTime())?-1:1; 
//            }
//        }
//        return 1;
        
        if (o.getDate().before(this.getDate())) {
            return -1;
        }else if(o.getDate().after(this.getDate())){
            return 1;
        }else {
            return o.getTime().before(this.getTime())?-1:1;
        }
    }
    
}
