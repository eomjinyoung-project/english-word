package com.eomjinyoung.ew.service;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import com.eomjinyoung.ew.dao.TestWordDao;
import com.eomjinyoung.ew.domain.TestWord;

@Service
public class TestWordService {
  
  TestWordDao testWordDao;
  
  public TestWordService(TestWordDao testWordDao) {
    this.testWordDao = testWordDao;
  }
  

  public List<TestWord> getTestWordsInWordGroup(int wordGroupNo, String wordName) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("wordName", wordName);
    paramMap.put("wordGroupNo", wordGroupNo);
    
    return testWordDao.findTestWord(paramMap);
  }
  
  public int add(TestWord testWord) {
    return testWordDao.insert(testWord);
  }
}
