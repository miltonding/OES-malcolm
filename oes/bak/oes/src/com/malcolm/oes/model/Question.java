package com.malcolm.oes.model;

public class Question {

    private int id;
    private String questionTitle;
    private String questionId;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String answer;
    private int status;

    public Question() {}

    public Question(String questionTitle, String questionId, String answerA, String answerB, String answerC,
            String answerD, String answer, int status) {
        super();
        this.questionTitle = questionTitle;
        this.questionId = questionId;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.answer = answer;
        this.status = status;
    }
    public String getQuestionTitle() {
        return questionTitle;
    }
    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getQuestionId() {
        return questionId;
    }
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
    public String getAnswerA() {
        return answerA;
    }
    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }
    public String getAnswerB() {
        return answerB;
    }
    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }
    public String getAnswerC() {
        return answerC;
    }
    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }
    public String getAnswerD() {
        return answerD;
    }
    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

}
