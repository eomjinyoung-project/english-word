package com.eomjinyoung.ew.domain;

import java.sql.Date;

public class VocabularyTest {
  
  protected int no;
  protected Date testingDate;
  protected int step;
  protected Word word;
  protected WordGroup wordGroup;
  protected User user;
  
  public VocabularyTest() {
  }

  @Override
  public String toString() {
    return "VocabularyTest [no=" + no + ", testingDate=" + testingDate + ", step=" + step
        + ", word=" + word + ", wordGroup=" + wordGroup + ", user=" + user + "]";
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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
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
