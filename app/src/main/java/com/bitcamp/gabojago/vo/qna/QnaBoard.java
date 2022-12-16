package com.bitcamp.gabojago.vo.qna;

import java.sql.Date;

public class QnaBoard {

    private Integer no;
    private String id;
    private Boolean disclosure;
    private String title;
    private String questionContent;
    private Date createdDate;
    private String answerContent;
    private String answerDate;

    public QnaBoard() {

    }
    @Override
    public String toString() {
        return "QnaBoard{" +
                "no=" + no +
                ", id='" + id + '\'' +
                ", disclosure=" + disclosure +
                ", title='" + title + '\'' +
                ", questionContent='" + questionContent + '\'' +
                ", createdDate=" + createdDate +
                ", answerContent='" + answerContent + '\'' +
                ", answerDate='" + answerDate + '\'' +
                '}';
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getDisclosure() {
        return disclosure;
    }

    public void setDisclosure(Boolean disclosure) {
        this.disclosure = disclosure;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public String getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(String answerDate) {
        this.answerDate = answerDate;
    }
}
