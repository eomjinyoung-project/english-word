package com.eomjinyoung.ew.domain;

import java.sql.Date;

public class Word {
  protected int no;
  protected String name;
  protected String meaning;
  protected Date createdDate;
  
  public Word() {
  }
  
  public Word(String name, String meaning) {
    this.name = name;
    this.meaning = meaning;
  }

  @Override
  public String toString() {
    return "Word [no=" + no + ", name=" + name + ", meaning=" + meaning + ", createdDate="
        + createdDate + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMeaning() {
    return meaning;
  }

  public void setMeaning(String meaning) {
    this.meaning = meaning;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
  
  
}
