package com.eomjinyoung.ew.command.interceptor;

import org.springframework.stereotype.Component;
import com.eomjinyoung.ew.AppSession;
import com.eomjinyoung.ew.command.Command;
import com.eomjinyoung.ew.command.CommandInterceptor;
import com.eomjinyoung.ew.domain.User;

@Component
public class AuthInterceptor implements CommandInterceptor {

  AppSession appSession;
  
  public AuthInterceptor(AppSession appSession) {
    this.appSession = appSession;
  }
  
  @Override
  public boolean preHandle(String input, Command command) {
    
    String value = input.toLowerCase();
    
    switch (value) {
      case "test":
      case "whoami":
        if (!isLoggedIn()) {
          System.out.println("로그인 하시기 바랍니다.");
          return false;
        }
        break;
    }
    return true;
  }
  
  private boolean isLoggedIn() {
    User member = (User) appSession.getAttribute("user");
    
    if (member == null)
      return false;
    else
      return true;
  }

}
