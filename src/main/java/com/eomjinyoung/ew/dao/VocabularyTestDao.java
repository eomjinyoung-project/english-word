package com.eomjinyoung.ew.dao;

import java.util.List;
import java.util.Map;
import com.eomjinyoung.ew.domain.VocabularyTest;

public interface VocabularyTestDao {
  List<VocabularyTest> findVocabularyTest(Map<String,Object> paramMap);
  int update(Map<String,Object> paramMap);
  int countNewVocabularyTest(Map<String,Object> paramMap);
  int insertAllOfNewVocabularyTest(Map<String,Object> paramMap);
}
