package com.clin.entity;


import java.io.Serializable;

public class Primessage implements Serializable {

  private String pMid;
  private String uid;
  private String rUid;
  private String pMcontent;
  private java.sql.Timestamp pMdatetime;


  public String getPMid() {
    return pMid;
  }

  public void setPMid(String pMid) {
    this.pMid = pMid;
  }


  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }


  public String getRUid() {
    return rUid;
  }

  public void setRUid(String rUid) {
    this.rUid = rUid;
  }


  public String getPMcontent() {
    return pMcontent;
  }

  public void setPMcontent(String pMcontent) {
    this.pMcontent = pMcontent;
  }


  public java.sql.Timestamp getPMdatetime() {
    return pMdatetime;
  }

  public void setPMdatetime(java.sql.Timestamp pMdatetime) {
    this.pMdatetime = pMdatetime;
  }

}
