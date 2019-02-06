package com.eomjinyoung.ew.command;

import java.util.HashMap;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.eomjinyoung.ew.AppSession;
import com.eomjinyoung.ew.domain.Member;
import com.eomjinyoung.ew.service.MemberService;

@Component("signin")
public class SignInCommand implements Command {

  Scanner keyboard;
  MemberService memberService;
  AppSession appSession;
  
  public SignInCommand(
      MemberService memberService, 
      @Qualifier("keyboard") Scanner keyboard,
      AppSession appSession) {
    this.memberService = memberService;
    this.keyboard = keyboard;
    this.appSession = appSession;
  }
  
  @Override
  public void execute() {
    HashMap<String,Object> params = new HashMap<>();
    
    System.out.print("Username: ");
    params.put("username", keyboard.nextLine());
    
    System.out.print("Password: ");
    params.put("password", keyboard.nextLine());
    
    try {
      Member member = memberService.signIn(params);
      if (member == null) {
        System.out.println("사용자 이름 또는 암호가 맞지 않습니다.");
        return;
      }
      
      appSession.setAttribute("user", member);
      System.out.printf("%s님 반갑습니다\n", member.getUsername());
      
    } catch (Exception e) {
      System.out.println("회원 가입 중에 오류 발생.");
      e.printStackTrace();
    }
  }
}
