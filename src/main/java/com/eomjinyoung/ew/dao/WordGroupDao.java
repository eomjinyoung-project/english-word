package com.eomjinyoung.ew.dao;

import java.util.List;
import com.eomjinyoung.ew.domain.Word;
import com.eomjinyoung.ew.domain.WordGroup;

public interface WordGroupDao {
  int insert(WordGroup wordGroup);
  Word findByNo(int no);
  List<WordGroup> findAll();
  int update(WordGroup wordGroup); 
  int delete(int no);
}
