package com.bitcamp.gabojago.vo;

import java.sql.Date;
import java.util.List;

public class Recommendation {

  private int recono; // 코스추천글번호
  private String title; // 코스추천글제목
  private Date wdate; // 코스추천글 등록일
  private int cnt; // 조회수
  private Boolean act; // 활성여부
  private Boolean pet; // 반려동물동반가능여부
  private Boolean frd; // 친구동행추천
  private Boolean cple; // 연인동행추천
  private Boolean fmly; // 가족동행추천
  private Boolean solo; // 혼자여행추천
  private String tpname; // 이동수단명
  private Member writer; // 글쓴 회원정보

  private String path; // 파일경로

  private List<JangSoReview> jangSoReviews;

  public int getRecono() {
    return recono;
  }

  public void setRecono(int recono) {
    this.recono = recono;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getWdate() {
    return wdate;
  }

  public void setWdate(Date wdate) {
    this.wdate = wdate;
  }

  public int getCnt() {
    return cnt;
  }

  public void setCnt(int cnt) {
    this.cnt = cnt;
  }

  public Boolean getAct() {
    return act;
  }

  public void setAct(Boolean act) {
    this.act = act;
  }

  public Boolean getPet() {
    return pet;
  }

  public void setPet(Boolean pet) {
    this.pet = pet;
  }

  public Boolean getFrd() {
    return frd;
  }

  public void setFrd(Boolean frd) {
    this.frd = frd;
  }

  public Boolean getCple() {
    return cple;
  }

  public void setCple(Boolean cple) {
    this.cple = cple;
  }

  public Boolean getFmly() {
    return fmly;
  }

  public void setFmly(Boolean fmly) {
    this.fmly = fmly;
  }

  public Boolean getSolo() {
    return solo;
  }

  public void setSolo(Boolean solo) {
    this.solo = solo;
  }

  public String getTpname() {
    return tpname;
  }

  public void setTpname(String tpname) {
    this.tpname = tpname;
  }

  public Member getWriter() {
    return writer;
  }

  public void setWriter(Member writer) {
    this.writer = writer;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public List<JangSoReview> getJangSoReviews() {
    return jangSoReviews;
  }

  public void setJangSoReviews(List<JangSoReview> jangSoReviews) {
    this.jangSoReviews = jangSoReviews;
  }

  @Override
  public String toString() {
    return "Recommendation{" +
            "recono=" + recono +
            ", title='" + title + '\'' +
            ", wdate=" + wdate +
            ", cnt=" + cnt +
            ", act=" + act +
            ", pet=" + pet +
            ", frd=" + frd +
            ", cple=" + cple +
            ", fmly=" + fmly +
            ", solo=" + solo +
            ", tpname='" + tpname + '\'' +
            ", writer=" + writer +
            ", path='" + path + '\'' +
            ", jangSoReviews=" + jangSoReviews +
            '}';
  }
}