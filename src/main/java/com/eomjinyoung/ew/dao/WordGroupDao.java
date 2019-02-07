package com.eomjinyoung.ew.dao;

import java.util.List;
import com.eomjinyoung.ew.domain.Word;
import com.eomjinyoung.ew.domain.WordGroup;

public interface WordGroupDao {
  List<WordGroup> findAll();
  Word findById(int id);
  int insert(WordGroup wordGroup);
  int update(WordGroup wordGroup); 
  int delete(int id);
}
