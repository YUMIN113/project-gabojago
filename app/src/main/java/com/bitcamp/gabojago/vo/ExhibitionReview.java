package com.bitcamp.gabojago.vo;

import java.sql.Date;

public class ExhibitionReview {

  private int rvno; //리뷰번호
  private String cont; // 리뷰 내용
  private Date wdate; // 작성일
  private String id; // 아이디
  private int exno; // 전시회 번호

  private Member writer; // 회원 id

  @Override
  public String toString() {
    return "ExhibitionReview{" +
        "rvno=" + rvno +
        ", cont='" + cont + '\'' +
        ", wdate=" + wdate +
        ", id='" + id + '\'' +
        ", exno=" + exno +
        ", writer=" + writer +
        '}';
  }

  public Member getWriter() {
    return writer;
  }

  public void setWriter(Member writer) {
    this.writer = writer;
  }

  public int getRvno() {
    return rvno;
  }

  public void setRvno(int rvno) {
    this.rvno = rvno;
  }

  public String getCont() {
    return cont;
  }

  public void setCont(String cont) {
    this.cont = cont;
  }

  public Date getWdate() {
    return wdate;
  }

  public void setWdate(Date wdate) {
    this.wdate = wdate;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getExno() {
    return exno;
  }

  public void setExno(int exno) {
    this.exno = exno;
  }

}
