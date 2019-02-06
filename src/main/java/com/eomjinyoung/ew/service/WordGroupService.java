package com.eomjinyoung.ew.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.eomjinyoung.ew.dao.WordGroupDao;
import com.eomjinyoung.ew.domain.WordGroup;

@Service
public class WordGroupService {
  
  WordGroupDao wordGroupDao;
  
  public WordGroupService(WordGroupDao wordGroupDao) {
    this.wordGroupDao = wordGroupDao;
  }
  
  public List<WordGroup> list() {
    return wordGroupDao.findAll();
  }
  
}
