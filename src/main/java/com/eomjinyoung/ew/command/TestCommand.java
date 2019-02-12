package com.eomjinyoung.ew.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.eomjinyoung.ew.AppSession;
import com.eomjinyoung.ew.domain.User;
import com.eomjinyoung.ew.domain.StudyStep;
import com.eomjinyoung.ew.domain.VocabularyTest;
import com.eomjinyoung.ew.domain.WordGroup;
import com.eomjinyoung.ew.service.VocabularyTestService;
import com.eomjinyoung.ew.service.WordGroupService;

@Component("test")
public class TestCommand implements Command {

  StudyStep[] studySteps = {
    new StudyStep(0, "시작일", 1),
    new StudyStep(1, "1일", 1),
    new StudyStep(2, "2일", 1),
    new StudyStep(3, "3일", 3),
    new StudyStep(4, "3일 후", 7),
    new StudyStep(5, "7일 후", 15),
    new StudyStep(6, "15일 후", 30),
    new StudyStep(7, "30일 후", 7)
  };
  
  Scanner keyboard;
  AppSession appSession;
  VocabularyTestService vocabularyTestService;
  WordGroupService wordGroupService;
  
  public TestCommand(
      AppSession appSession, 
      VocabularyTestService testWordService, 
      WordGroupService wordGroupService,
      @Qualifier("keyboard") Scanner keyboard) {
    this.appSession = appSession;
    this.vocabularyTestService = testWordService;
    this.wordGroupService = wordGroupService;
    this.keyboard = keyboard;
  }
  
  @Override
  public void execute() {
    User member = (User) appSession.getAttribute("user");
    
    int wordGroupNo = selectWordGroup();
    
    vocabularyTestService.prepareVocabularyTest(member.getNo(), wordGroupNo);
    
    for (int step = 7; step > 0; step--) {
      testStepWords(member.getNo(), wordGroupNo, step);
    }
    
    testNewWords(member.getNo(), wordGroupNo);
  }
  
  private int selectWordGroup() {
    List<WordGroup> wordGroups = wordGroupService.list();
    
    System.out.println("시험 볼 단어집을 선택하세요.\n");
    
    printWordGroup(wordGroups);
    
    int wordGroupNo = 0;
    while (true) {
      System.out.print("단어집 번호? ");
      wordGroupNo = Integer.parseInt(keyboard.nextLine());
      
      if (validate(wordGroups, wordGroupNo))
        break;
    }
    System.out.println();
    
    return wordGroupNo;
  }
  
  private void printWordGroup(List<WordGroup> wordGroups) {
    for (WordGroup wg : wordGroups) {
      System.out.printf("%d. %s(%d)\n", wg.getNo(), wg.getTitle(), wg.getSize());
    }
    System.out.println();
  }
  
  private boolean validate(List<WordGroup> wordGroups, int wordGroupNo) {
    for (WordGroup wg : wordGroups) {
      if (wg.getNo() == wordGroupNo)
        return true;
    }
    return false;
  }
  
  private void testStepWords(int memberNo, int wordGroupNo, int step) {
    StudyStep studyStep = studySteps[step];
    List<VocabularyTest> testWords = vocabularyTestService.getTodayVocabularyTest(memberNo, wordGroupNo, step);
    
    if (testWords.size() == 0)
      return;
    
    System.out.printf("[%d 단계 - %s (%d 개 단어)]\n", 
        studyStep.getNo(), studyStep.getTitle(), testWords.size());
    
    List<VocabularyTest> passWords = new ArrayList<>();
    List<VocabularyTest> failWords = new ArrayList<>();
    
    boolean isPutOff = false;
    int count = 0;
    for (VocabularyTest vocabularyTest : testWords) {
      if (isPutOff) {
        failWords.add(vocabularyTest);
        continue;
      }
        
      System.out.printf("%s (%d)", vocabularyTest.getWord().getName(), ++count);
      
      String input = keyboard.nextLine();
      System.out.printf("  %s : ", vocabularyTest.getWord().getMeaning());
      
      input = keyboard.nextLine();
      
      if ("q".equals(input)) {
        isPutOff = true;
      } else if ("x".equalsIgnoreCase(input)) {
        failWords.add(vocabularyTest);
      } else {
        passWords.add(vocabularyTest);
      }
    }
    System.out.println();
    
    vocabularyTestService.increaseVocabularyTestStep(passWords, studyStep);
    vocabularyTestService.retestVocabularyTestStep(failWords, studyStep);
  }
  
  private void testNewWords(int memberNo, int wordGroupNo) {
    List<VocabularyTest> vocabularyTests = vocabularyTestService.getNewVocabularyTest(memberNo, wordGroupNo);
    
    if (vocabularyTests.size() == 0)
      return;
    
    System.out.printf("[새로 암기할 단어 %d 개]\n", vocabularyTests.size());
    
    List<VocabularyTest> passWords = new ArrayList<>();
    
    int count = 0;
    for (VocabularyTest vocabularyTest : vocabularyTests) {
        
      System.out.printf("%s (%d)", vocabularyTest.getWord().getName(), ++count);
      
      String input = keyboard.nextLine();
      System.out.printf("  %s : ", vocabularyTest.getWord().getMeaning());
      
      passWords.add(vocabularyTest);
      
      input = keyboard.nextLine();
      
      if ("q".equals(input)) {
        break;
      }
    }
    System.out.println();
    
    vocabularyTestService.increaseVocabularyTestStep(passWords, studySteps[0]);
  }  
  
}
