package com.eomjinyoung.ew.command;

import org.springframework.stereotype.Component;
import com.eomjinyoung.ew.AppSession;
import com.eomjinyoung.ew.domain.Member;

@Component("whoami")
public class WhoamiCommand implements Command {

  AppSession appSession;
  
  public WhoamiCommand(AppSession appSession) {
    this.appSession = appSession;
  }
  
  @Override
  public void execute() {
    Member member = (Member) appSession.getAttribute("user");
    
    if (member != null) {
      System.out.printf("%s(%s)\n", member.getUsername(), member.getEmail());
    }
  }
}
