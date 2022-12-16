package com.bitcamp.gabojago.vo;

import java.util.List;

public class JangSoReview {

  private int prvno; // 장소후기번호
  private String cont; // 내용
  private int recono; // 코스추천글번호
  private String plname; // 장소이름
  private String adrs; // 장소주소
  private List<JangSoReviewAttachedFile> attachedFiles; // 첨부파일 List를 저장하는 필드

  public int getPrvno() {
    return prvno;
  }

  public void setPrvno(int prvno) {
    this.prvno = prvno;
  }

  public String getCont() {
    return cont;
  }

  public void setCont(String cont) {
    this.cont = cont;
  }

  public int getRecono() {
    return recono;
  }

  public void setRecono(int recono) {
    this.recono = recono;
  }

  public String getPlname() {
    return plname;
  }

  public void setPlname(String plname) {
    this.plname = plname;
  }

  public String getAdrs() {
    return adrs;
  }

  public void setAdrs(String adrs) {
    this.adrs = adrs;
  }

  public List<JangSoReviewAttachedFile> getAttachedFiles() {
    return attachedFiles;
  }

  public void setAttachedFiles(
      List<JangSoReviewAttachedFile> attachedFiles) {
    this.attachedFiles = attachedFiles;
  }

  @Override
  public String toString() {
    return "JangSoReview{" +
        "prvno=" + prvno +
        ", cont='" + cont + '\'' +
        ", recono=" + recono +
        ", plname='" + plname + '\'' +
        ", address='" + adrs + '\'' +
        ", attachedFiles=" + attachedFiles +
        '}';
  }
}
