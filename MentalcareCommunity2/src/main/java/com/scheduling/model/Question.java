package com.scheduling.model;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionId;
    
    @NotEmpty
    private String userQuestion;
    
    private Time questionTime;
    
    private Date questionDate;
    
//    @OneToMany(mappedBy = "question")
//    @JsonManagedReference
//    private List<FileList> pathList;

    @OneToMany(mappedBy = "question",fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Answer> answers;
    
    @ManyToOne
    @JsonManagedReference
    private UserEntity userEntity;

    public Question() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Question(long questionId, @NotEmpty String userQuestion, UserEntity userEntity) {
        super();
        this.questionId = questionId;
        this.userQuestion = userQuestion;
        this.userEntity = userEntity;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getUserQuestion() {
        return userQuestion;
    }

    public void setUserQuestion(String userQuestion) {
        this.userQuestion = userQuestion;
    }


    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
    
    public Time getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(Time questionTime) {
        this.questionTime = questionTime;
    }

    public Date getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(Date questionDate) {
        this.questionDate = questionDate;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question [questionId=" + questionId + ", userQuestion=" + userQuestion + ", userEntity=" + userEntity
                + "]";
    }

//    public List<FileList> getPathList() {
//        return pathList;
//    }
//
//    public void setPathList(List<FileList> pathList) {
//        this.pathList = pathList;
//    }

//    @Override
//    public String toString() {
//        return "Question [questionId=" + questionId + ", userQuestion=" + userQuestion + ", pathList=" + pathList
//                + ", userEntity=" + userEntity + "]";
//    }
//    
    
    
}
