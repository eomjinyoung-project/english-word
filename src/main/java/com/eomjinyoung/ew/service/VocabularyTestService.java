package com.eomjinyoung.ew.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.eomjinyoung.ew.dao.VocabularyTestDao;
import com.eomjinyoung.ew.domain.StudyStep;
import com.eomjinyoung.ew.domain.VocabularyTest;

@Service
public class VocabularyTestService {
  
  VocabularyTestDao vocabularyTestDao;
  
  public VocabularyTestService(VocabularyTestDao vocabularyTestDao) {
    this.vocabularyTestDao = vocabularyTestDao;
  }
  
  public void prepareVocabularyTest(int userNo, int wordGroupNo) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("userNo", userNo);
    paramMap.put("wordGroupNo", wordGroupNo);
    
    if (vocabularyTestDao.countNewVocabularyTest(paramMap) == 0) 
      return;
    
    vocabularyTestDao.insertAllOfNewVocabularyTest(paramMap);
  }
  
  public List<VocabularyTest> getTodayVocabularyTest(int userNo, int wordGroupNo, int step) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("userNo", userNo);
    paramMap.put("wordGroupNo", wordGroupNo);
    paramMap.put("step", step);
    paramMap.put("testingDate", new Date(System.currentTimeMillis()).toString());
    
    return vocabularyTestDao.findVocabularyTest(paramMap);
  }
  
  public List<VocabularyTest> getNewVocabularyTest(int userNo, int wordGroupNo) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("userNo", userNo);
    paramMap.put("wordGroupNo", wordGroupNo);
    paramMap.put("step", 0);
    
    return vocabularyTestDao.findVocabularyTest(paramMap);
  }
  
  public void increaseVocabularyTestStep(List<VocabularyTest> vocabularyTests, StudyStep currStep) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("addingDays", currStep.getNextIncreasingDays());
    paramMap.put("step", currStep.getNo() + 1);
    
    updateVocabularyTestStep(vocabularyTests, paramMap);
  }
  
  public void retestVocabularyTestStep(List<VocabularyTest> vocabularyTests, StudyStep currStep) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("addingDays", 1);
    paramMap.put("step", currStep.getNo());
    
    updateVocabularyTestStep(vocabularyTests, paramMap);
  }
  
  private void updateVocabularyTestStep(List<VocabularyTest> vocabularyTests, Map<String,Object> paramMap) {
    for (VocabularyTest testWord : vocabularyTests) {
      paramMap.put("no", testWord.getNo());
      vocabularyTestDao.update(paramMap);
    }
  }

}
