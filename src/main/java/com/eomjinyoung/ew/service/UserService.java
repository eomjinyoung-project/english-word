package com.eomjinyoung.ew.service;

import java.util.Map;
import org.springframework.stereotype.Service;
import com.eomjinyoung.ew.dao.UserDao;
import com.eomjinyoung.ew.domain.User;

@Service
public class UserService {
  
  UserDao userDao;
  
  public UserService(UserDao userDao) {
    this.userDao = userDao;
  }
  
  public void signUp(User user) {
    userDao.insert(user);
  }
  
  public User signIn(Map<String,Object> params) {
    return userDao.findByUsernameAndPassword(params);
  }
}
