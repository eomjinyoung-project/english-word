package com.eomjinyoung.ew.dao;

import java.util.List;
import java.util.Map;
import com.eomjinyoung.ew.domain.Word;

public interface WordDao {
  int insert(Word word);
  Word findByNo(int no);
  List<Word> findAll();
  List<Word> findByName(String name);
  int update(Word word); 
  int delete(int no);
  int insertInGroupByWordNo(Map<String,Object> paramMap);
  int insertInGroupByWordName(Map<String,Object> paramMap);
}
