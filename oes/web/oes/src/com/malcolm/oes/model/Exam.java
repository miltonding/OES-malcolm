package com.malcolm.oes.model;

import java.util.Date;

public class Exam {

    private int id;
    private String examId;
    private String examName;
    private String examDescription;
    private String examDuration;
    private int questionQuantity;
    private int questionPoint;
    private int totalScore;
    private int passCriteria;
    private int isDraft;
    private float passRate;
    private float averageScore;
    private Date effectiveTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotalScore() {
        if (totalScore == 0) {
            return getQuestionQuantity() * getQuestionPoint();
        }
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getPassCriteria() {
        return passCriteria;
    }

    public void setPassCriteria(int passCriteria) {
        this.passCriteria = passCriteria;
    }

    public float getPassRate() {
        return passRate;
    }

    public void setPassRate(float passRate) {
        this.passRate = passRate;
    }

    public float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamDescription() {
        return examDescription;
    }

    public void setExamDescription(String examDescription) {
        this.examDescription = examDescription;
    }

    public String getExamDuration() {
        return examDuration;
    }

    public void setExamDuration(String examDuration) {
        this.examDuration = examDuration;
    }

    public int getQuestionQuantity() {
        return questionQuantity;
    }

    public void setQuestionQuantity(int questionQuantity) {
        this.questionQuantity = questionQuantity;
    }

    public int getQuestionPoint() {
        return questionPoint;
    }

    public void setQuestionPoint(int questionPoint) {
        this.questionPoint = questionPoint;
    }

    public String getExamId() {
        if (id != 0) {
            return String.format("E%06d", getId());
        }
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public int getIsDraft() {
        return isDraft;
    }

    public void setIsDraft(int isDraft) {
        this.isDraft = isDraft;
    }
}
