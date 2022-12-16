package com.bitcamp.gabojago.vo;

import java.sql.Date;
import java.util.List;

public class Exhibition {

  private int exno;
  private String exname;// 전시 이름
  private String cont; // 내용
  private Date wdate; // 게시글 등록일
  private int plno; // 장소번호
  private Date stdate; // 시작날짜
  private Date eddate; // 종료일
  private int price; // 가격

// 첨부파일 정보를 저장할 필드
private List<ExhibitionFile> exhibitionFiles;


  public int getExno() {
    return exno;
  }

  public void setExno(int exno) {
    this.exno = exno;
  }

  public String getExname() {
    return exname;
  }

  public void setExname(String exname) {
    this.exname = exname;
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

  public int getPlno() {
    return plno;
  }

  public void setPlno(int plno) {
    this.plno = plno;
  }

  public Date getStdate() {
    return stdate;
  }

  public void setStdate(Date stdate) {
    this.stdate = stdate;
  }

  public Date getEddate() {
    return eddate;
  }

  public void setEddate(Date eddate) {
    this.eddate = eddate;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public List<ExhibitionFile> getExhibitionFiles() {
    return exhibitionFiles;
  }

  public void setExhibitionFiles(List<ExhibitionFile> exhibitionFiles) {
    this.exhibitionFiles = exhibitionFiles;
  }

  @Override
  public String toString() {
    return "Exhibition{" +
        "exno=" + exno +
        ", exname='" + exname + '\'' +
        ", cont='" + cont + '\'' +
        ", wdate=" + wdate +
        ", plno=" + plno +
        ", stdate=" + stdate +
        ", eddate=" + eddate +
        ", price=" + price +
        '}';
  }
}
