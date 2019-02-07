package com.eomjinyoung.ew.dao;

import java.util.List;
import com.eomjinyoung.ew.domain.Word;

public interface WordDao {
  List<Word> findAll();
  Word findById(int id);
  List<Word> findByName(String name);
  int insert(Word word);
  int update(Word word); 
  int delete(int id);
}
