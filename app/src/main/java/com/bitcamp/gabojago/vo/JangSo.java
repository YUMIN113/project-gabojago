package com.bitcamp.gabojago.vo;

import java.sql.Date;

public class JangSo {

  private int plno; // 장소번호
  private String plname; // 장소명
  private String qcall; // 문의번호
  private int postno; // 우편번호
  private String basicad; // 기본주소
  private String detailad; // 상세주소
  private String wido; // 위도
  private String kdo; // 경도

// 첨부파일 정보를 저장할 필드


  public int getPlno() {
    return plno;
  }

  public void setPlno(int plno) {
    this.plno = plno;
  }

  public String getPlname() {
    return plname;
  }

  public void setPlname(String plname) {
    this.plname = plname;
  }

  public String getQcall() {
    return qcall;
  }

  public void setQcall(String qcall) {
    this.qcall = qcall;
  }

  public int getPostno() {
    return postno;
  }

  public void setPostno(int postno) {
    this.postno = postno;
  }

  public String getBasicad() {
    return basicad;
  }

  public void setBasicad(String basicad) {
    this.basicad = basicad;
  }

  public String getDetailad() {
    return detailad;
  }

  public void setDetailad(String detailad) {
    this.detailad = detailad;
  }

  public String getWido() {
    return wido;
  }

  public void setWido(String wido) {
    this.wido = wido;
  }

  public String getKdo() {
    return kdo;
  }

  public void setKdo(String kdo) {
    this.kdo = kdo;
  }

  @Override
  public String toString() {
    return "JangSo{" +
            "plno=" + plno +
            ", plname='" + plname + '\'' +
            ", qcall='" + qcall + '\'' +
            ", postno=" + postno +
            ", basicad='" + basicad + '\'' +
            ", detailad='" + detailad + '\'' +
            ", wido='" + wido + '\'' +
            ", kdo='" + kdo + '\'' +
            '}';
  }
}
