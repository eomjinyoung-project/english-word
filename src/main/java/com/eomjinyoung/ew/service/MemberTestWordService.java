package com.eomjinyoung.ew.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.eomjinyoung.ew.dao.MemberTestWordDao;
import com.eomjinyoung.ew.domain.StudyStep;
import com.eomjinyoung.ew.domain.MemberTestWord;

@Service
public class MemberTestWordService {
  
  MemberTestWordDao memberTestWordDao;
  
  public MemberTestWordService(MemberTestWordDao testWordDao) {
    this.memberTestWordDao = testWordDao;
  }
  
  public void prepareTestWords(int memberNo, int wordGroupNo) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("memberNo", memberNo);
    paramMap.put("wordGroupNo", wordGroupNo);
    
    if (memberTestWordDao.countNewTestWord(paramMap) == 0) 
      return;
    
    memberTestWordDao.insertAllNewTestWord(paramMap);
  }
  
  public List<MemberTestWord> getTodayTestWords(int memberNo, int wordGroupNo, int step) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("memberNo", memberNo);
    paramMap.put("wordGroupNo", wordGroupNo);
    paramMap.put("step", step);
    paramMap.put("testingDate", new Date(System.currentTimeMillis()).toString());
    
    return memberTestWordDao.findTestingWord(paramMap);
  }
  
  public List<MemberTestWord> getNewTestWords(int memberNo, int wordGroupNo) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("memberNo", memberNo);
    paramMap.put("wordGroupNo", wordGroupNo);
    paramMap.put("step", 0);
    
    return memberTestWordDao.findTestingWord(paramMap);
  }
  
  public void increaseUserTestWordStep(List<MemberTestWord> testWords, StudyStep currStep) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("addingDays", currStep.getNextIncreasingDays());
    paramMap.put("step", currStep.getNo() + 1);
    
    updateUserTestWordStep(testWords, paramMap);
  }
  
  public void retestUserTestWordStep(List<MemberTestWord> testWords, StudyStep currStep) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("addingDays", 1);
    paramMap.put("step", currStep.getNo());
    
    updateUserTestWordStep(testWords, paramMap);
  }
  
  private void updateUserTestWordStep(List<MemberTestWord> testWords, Map<String,Object> paramMap) {
    for (MemberTestWord testWord : testWords) {
      paramMap.put("no", testWord.getNo());
      memberTestWordDao.update(paramMap);
    }
  }

}
