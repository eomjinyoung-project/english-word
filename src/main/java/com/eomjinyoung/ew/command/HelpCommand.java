package com.eomjinyoung.ew.command;

import org.springframework.stereotype.Component;

@Component("help")
public class HelpCommand implements Command {

  @Override
  public void execute() {
    System.out.println("사용할 수 있는 명령:");
    System.out.println("help - 도움말");
    System.out.println("signup - 회원 가입");
    System.out.println("signin - 로그인");
    System.out.println("signout - 로그아웃");
    System.out.println("test - 단어 연습");
    System.out.println("whoami - 로그인 사용자 정보");
  }

}
