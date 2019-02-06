package com.eomjinyoung.ew.dao;

import java.util.List;
import java.util.Map;
import com.eomjinyoung.ew.domain.MemberTestWord;

public interface MemberTestWordDao {
  List<MemberTestWord> findTestingWord(Map<String,Object> paramMap);
  int update(Map<String,Object> paramMap);
  int countNewTestWord(Map<String,Object> paramMap);
  int insertAllNewTestWord(Map<String,Object> paramMap);
}
