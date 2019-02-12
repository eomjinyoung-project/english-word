package com.eomjinyoung.ew.domain;

public class WordGroup {
  protected int no;
  protected String title;
  protected int size;
  
  public WordGroup() {
  }
  
  public WordGroup(int no, String title) {
    this.no = no;
    this.title = title;
  }

  @Override
  public String toString() {
    return "WordGroup [no=" + no + ", title=" + title + ", size=" + size + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  
}
