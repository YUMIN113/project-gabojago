package com.bitcamp.gabojago.vo;

import java.sql.Date;

public class ExhibitionFile {

  private int exfno; // 전시회 첨부파일 번호
  private String path; // 전시회 첨부파일 경로
  private int exno; // 전시회 게시글 번호
  private String fname; // 전시회 파일 명

public ExhibitionFile(){}

  public ExhibitionFile( String path,String fname){
    this.path = path;
  this.fname= fname;
  }
/*
  public ExhibitionFile(String fname) {
    this.fname = fname;
  }*/


  public int getExfno() {
    return exfno;
  }

  public void setExfno(int exfno) {
    this.exfno = exfno;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public int getExno() {
    return exno;
  }

  public void setExno(int exno) {
    this.exno = exno;
  }

  public String getFname() {
    return fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }

  @Override
  public String toString() {
    return "ExhibitionFile{" +
        "exfno=" + exfno +
        ", path='" + path + '\'' +
        ", exno=" + exno +
        ", fname='" + fname + '\'' +
        '}';
  }
}
