package com.eomjinyoung.ew.command;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.eomjinyoung.ew.domain.User;
import com.eomjinyoung.ew.service.UserService;

@Component("signup")
public class SignUpCommand implements Command {

  Scanner keyboard;
  UserService userService;
  
  public SignUpCommand(UserService memberService, @Qualifier("keyboard") Scanner keyboard) {
    this.userService = memberService;
    this.keyboard = keyboard;
  }
  
  @Override
  public void execute() {
    User member = new User();
    
    System.out.print("아이디: ");
    member.setUsername(keyboard.nextLine());
    
    System.out.print("이메일: ");
    member.setEmail(keyboard.nextLine());
    
    System.out.print("암호: ");
    member.setPassword(keyboard.nextLine());
    
    try {
      userService.signUp(member);
    } catch (Exception e) {
      System.out.println("회원 가입 중에 오류 발생.");
      e.printStackTrace();
      System.out.println();
    }
    
  }

}
