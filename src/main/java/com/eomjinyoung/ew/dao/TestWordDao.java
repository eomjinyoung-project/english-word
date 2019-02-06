package com.eomjinyoung.ew.dao;

import java.util.List;
import java.util.Map;
import com.eomjinyoung.ew.domain.TestWord;

public interface TestWordDao {
  List<TestWord> findTestWord(Map<String,Object> paramMap);
  int insert(TestWord testWord);
}
