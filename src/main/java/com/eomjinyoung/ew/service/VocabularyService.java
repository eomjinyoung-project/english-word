package com.eomjinyoung.ew.service;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import com.eomjinyoung.ew.dao.VocabularyDao;
import com.eomjinyoung.ew.domain.Vocabulary;

@Service
public class VocabularyService {
  
  VocabularyDao vocabularyDao;
  
  public VocabularyService(VocabularyDao vocabularyDao) {
    this.vocabularyDao = vocabularyDao;
  }
  

  public List<Vocabulary> getVocabularies(int wordGroupNo, String wordName) {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("wordName", wordName);
    paramMap.put("wordGroupNo", wordGroupNo);
    
    return vocabularyDao.findByWordGroupNoAndWordName(paramMap);
  }
  
  public int add(Vocabulary vocabulary) {
    return vocabularyDao.insert(vocabulary);
  }
}
