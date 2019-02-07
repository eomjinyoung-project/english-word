package com.eomjinyoung.ew.dao;

import java.util.List;
import java.util.Map;
import com.eomjinyoung.ew.domain.User;

public interface UserDao {
  List<User> findAll();
  User findById(int id);
  User findByUsernameAndPassword(Map<String,Object> params);
  int insert(User user);
  int update(User user); 
  int delete(int no);
}
