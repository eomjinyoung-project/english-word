package com.eomjinyoung.ew.domain;

import java.sql.Date;

public class MemberTestWord {
  
  protected int no;
  protected Date testingDate;
  protected int step;
  protected Word word;
  protected WordGroup wordGroup;
  protected Member member;
  
  public MemberTestWord() {
  }

  @Override
  public String toString() {
    return "MemberTestWord [no=" + no + ", testingDate=" + testingDate + ", step=" + step
        + ", word=" + word + ", wordGroup=" + wordGroup + ", member=" + member + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public Word getWord() {
    return word;
  }

  public void setWord(Word word) {
    this.word = word;
  }

  public WordGroup getWordGroup() {
    return wordGroup;
  }

  public void setWordGroup(WordGroup wordGroup) {
    this.wordGroup = wordGroup;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public Date getTestingDate() {
    return testingDate;
  }

  public void setTestingDate(Date testingDate) {
    this.testingDate = testingDate;
  }

  public int getStep() {
    return step;
  }

  public void setStep(int step) {
    this.step = step;
  }
  
}
