package com.eomjinyoung.ew.command;

import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.eomjinyoung.ew.domain.TestWord;
import com.eomjinyoung.ew.domain.Word;
import com.eomjinyoung.ew.domain.WordGroup;
import com.eomjinyoung.ew.service.TestWordService;
import com.eomjinyoung.ew.service.WordGroupService;
import com.eomjinyoung.ew.service.WordService;

@Component("add")
public class AddCommand implements Command {
  
  Scanner keyboard;
  WordService wordService;
  WordGroupService wordGroupService;
  TestWordService testWordService;
  
  public AddCommand(
      WordService wordService, 
      WordGroupService wordGroupService,
      TestWordService testWordService,
      @Qualifier("keyboard") Scanner keyboard) {
    this.wordService = wordService;
    this.wordGroupService = wordGroupService;
    this.testWordService = testWordService;
    this.keyboard = keyboard;
  }
  
  @Override
  public void execute() {
    WordGroup wordGroup = selectWordGroup();
    System.out.printf("[단어집 - %s]\n", wordGroup.getTitle());
    
    while (true) {
      String wordName = promptNewWord();
      
      if (wordName.length() == 0) {
        break;
      }
      
      List<TestWord> testWords = testWordService.getTestWordsInWordGroup(wordGroup.getNo(), wordName);
      
      if (testWords.size() > 0) {
        System.out.println("단어집에 이미 등록된 단어가 있습니다.");
        for (TestWord testWord : testWords) {
          System.out.printf("> %s = %s\n", testWord.getWord().getName(), testWord.getWord().getMeaning());
        }
        System.out.print("뜻을 추가하시겠습니까?(y/N)");
        
        String input = keyboard.nextLine();
        if (input.length() == 0 || !input.equalsIgnoreCase("y")) {
          continue;
        }
        
      } else {
        List<Word> words = wordService.getWords(wordName);
        
        if (words.size() > 0) {
          System.out.println("전체 사전에 이미 등록된 단어가 있습니다.");
          for (Word word : words) {
            System.out.printf("> %d. %s = %s\n", word.getNo(), word.getName(), word.getMeaning());
          }
          
          Word word = null;
          while (true) {
            System.out.print("단어집에 추가할 단어의 번호는?(없으면 그냥 엔터) ");
            String input = keyboard.nextLine();
            if (input.length() == 0)
              break;
            
            word = findWord(words, Integer.parseInt(input));
            if (word != null) {
              break;
            }
            
            System.out.println("단어의 번호가 맞지 않습니다.");
          }
          
          if (word != null) {
            testWordService.add(new TestWord(word.getNo(), wordGroup.getNo()));
            continue;
          }
        }
      }
      
      System.out.print("단어 뜻: ");
      String wordMeaning = keyboard.nextLine();
      if (wordMeaning.length() == 0)
        break;
      
      Word word = new Word(wordName, wordMeaning);
      wordService.addWord(word);
      testWordService.add(new TestWord(word.getNo(), wordGroup.getNo()));
    }
    
  }
  
  private String promptNewWord() {
    System.out.print("새 단어: ");
    return keyboard.nextLine();
  }
  
  private WordGroup selectWordGroup() {
    List<WordGroup> wordGroups = wordGroupService.list();
    
    System.out.println("관리할 단어집을 선택하세요.\n");
    
    printWordGroup(wordGroups);
    
    WordGroup wordGroup = null;
    while (true) {
      System.out.print("단어집 번호? ");
      int wordGroupNo = Integer.parseInt(keyboard.nextLine());
      
      wordGroup = findWordGroup(wordGroups, wordGroupNo);
      if (wordGroup != null) {
        break;
      }
    }
    System.out.println();
    
    return wordGroup;
  }
  
  private void printWordGroup(List<WordGroup> wordGroups) {
    for (WordGroup wg : wordGroups) {
      System.out.printf("%d. %s\n", wg.getNo(), wg.getTitle());
    }
    System.out.println();
  }
  
  private WordGroup findWordGroup(List<WordGroup> wordGroups, int wordGroupNo) {
    for (WordGroup wg : wordGroups) {
      if (wg.getNo() == wordGroupNo)
        return wg;
    }
    return null;
  }
  
  private Word findWord(List<Word> words, int wordNo) {
    for (Word w : words) {
      if (w.getNo() == wordNo)
        return w;
    }
    return null;
  }
  
}
