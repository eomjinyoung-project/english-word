package com.eomjinyoung.ew.domain;

import java.sql.Date;

public class Member {
  protected int no;
  protected String username;
  protected String email;
  protected String password;
  protected Date createdDate;
  
  @Override
  public String toString() {
    return "Member [no=" + no + ", username=" + username + ", email=" + email + ", password="
        + password + ", createdDate=" + createdDate + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
  
  
}
