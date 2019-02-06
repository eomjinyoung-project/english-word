package com.eomjinyoung.ew;

import java.util.HashMap;
import org.springframework.stereotype.Component;

@Component
public class AppSession {
  
  HashMap<String,Object> map = new HashMap<>();
  
  public void setAttribute(String name, Object value) {
    map.put(name, value);
  }
  
  public Object getAttribute(String name) {
    return map.get(name);
  }
  
  public void invalidate() {
    map.clear();
  }
}
