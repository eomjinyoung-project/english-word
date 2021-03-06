package com.eomjinyoung.ew.domain;

public class Vocabulary {
  protected int wordNo;
  protected int wordGroupNo;
  protected Word word;
  protected WordGroup wordGroup;
  
  public Vocabulary() {
  }

  public Vocabulary(int wordNo, int wordGroupNo) {
    this.wordNo = wordNo;
    this.wordGroupNo = wordGroupNo;
  }
  
  @Override
  public String toString() {
    return "Vocabulary [wordNo=" + wordNo + ", wordGroupNo=" + wordGroupNo + ", word=" + word
        + ", wordGroup=" + wordGroup + "]";
  }

  public int getWordNo() {
    return wordNo;
  }

  public void setWordNo(int wordNo) {
    this.wordNo = wordNo;
  }

  public int getWordGroupNo() {
    return wordGroupNo;
  }

  public void setWordGroupNo(int wordGroupNo) {
    this.wordGroupNo = wordGroupNo;
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
  
  
}
