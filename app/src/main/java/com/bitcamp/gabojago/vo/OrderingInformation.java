package com.bitcamp.gabojago.vo;

public class OrderingInformation {
  
  int extkno;
  
  String id;
  
  int exno;
  
  int cnt;
  
  String purdate;
  
  String payment;
  
  String ccdate;
  
  String crdate;
  
  public String getCrdate() {
    return crdate;
  }

  public void setCrdate(String crdate) {
    this.crdate = crdate;
  }

  public int getExtkno() {
    return extkno;
  }

  public String getCrDate() {
    return crdate;
  }

  public void setCrDate(String crDate) {
    this.crdate = crDate;
  }

  public void setExtkno(int extkno) {
    this.extkno = extkno;
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

  public int getCnt() {
    return cnt;
  }

  public void setCnt(int cnt) {
    this.cnt = cnt;
  }

  public String getPurdate() {
    return purdate;
  }

  public void setPurdate(String purDate) {
    this.purdate = purDate;
  }

  public String getPayment() {
    return payment;
  }

  public void setPayment(String payment) {
    this.payment = payment;
  }

  public String getCcdate() {
    return ccdate;
  }

  public void setCcdate(String ccDate) {
    this.ccdate = ccDate;
  }
}
