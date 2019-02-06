package com.eomjinyoung.ew.domain;

public class StudyStep {
  protected int no;
  protected String title;
  protected int nextIncreasingDays;
  
  public StudyStep() {
  }
  
  public StudyStep(int no, String title, int nextIncreasingDays) {
    this.no = no;
    this.title = title;
    this.nextIncreasingDays = nextIncreasingDays;
  }
  
  @Override
  public String toString() {
    return "StudyStep [no=" + no + ", title=" + title + ", nextIncreasingDays=" + nextIncreasingDays
        + "]";
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

  public int getNextIncreasingDays() {
    return nextIncreasingDays;
  }

  public void setNextIncreasingDays(int nextIncreasingDays) {
    this.nextIncreasingDays = nextIncreasingDays;
  }
  
  
}
