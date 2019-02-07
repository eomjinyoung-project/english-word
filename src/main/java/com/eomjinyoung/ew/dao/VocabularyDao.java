package com.eomjinyoung.ew.dao;

import java.util.List;
import java.util.Map;
import com.eomjinyoung.ew.domain.Vocabulary;

public interface VocabularyDao {
  List<Vocabulary> findByWordGroupNoAndWordName(Map<String,Object> paramMap);
  int insert(Vocabulary testWord);
  int insertByWordName(Map<String,Object> paramMap);
}
