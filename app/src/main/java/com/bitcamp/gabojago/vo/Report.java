package com.bitcamp.gabojago.vo;

import java.sql.Date;

public class Report {

  private int rpno;
  private int recono;
  private String id;
  private String rsn;
  private String rst;
  private Date rstdate;

  public int getRpno() {
    return rpno;
  }

  public void setRpno(int rpno) {
    this.rpno = rpno;
  }

  public int getRecono() {
    return recono;
  }

  public void setRecono(int recono) {
    this.recono = recono;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getRsn() {
    return rsn;
  }

  public void setRsn(String rsn) {
    this.rsn = rsn;
  }

  public String getRst() {
    return rst;
  }

  public void setRst(String rst) {
    this.rst = rst;
  }

  public Date getRstdate() {
    return rstdate;
  }

  public void setRstdate(Date rstdate) {
    this.rstdate = rstdate;
  }

  @Override
  public String toString() {
    return "Report{" +
        "rpno=" + rpno +
        ", recono=" + recono +
        ", id='" + id + '\'' +
        ", rsn='" + rsn + '\'' +
        ", rst='" + rst + '\'' +
        ", rstdate=" + rstdate +
        '}';
  }
}
