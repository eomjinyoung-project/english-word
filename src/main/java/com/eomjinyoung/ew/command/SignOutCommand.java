package com.eomjinyoung.ew.command;

import org.springframework.stereotype.Component;
import com.eomjinyoung.ew.AppSession;

@Component("signout")
public class SignOutCommand implements Command {

  AppSession appSession;
  
  public SignOutCommand(AppSession appSession) {
    this.appSession = appSession;
  }
  
  @Override
  public void execute() {
    appSession.invalidate();
    System.out.println("로그아웃 했습니다!");
  }
}
