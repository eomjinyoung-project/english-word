package com.eomjinyoung.ew;

import java.util.Scanner;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.eomjinyoung.ew.command.Command;
import com.eomjinyoung.ew.command.CommandInterceptor;


public class App {
  
  AnnotationConfigApplicationContext ctx;
  Scanner keyboard;
  
  public App() throws Exception {
    ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    keyboard = (Scanner) ctx.getBean("keyboard");
  }
  
  public void service() {
    
    while (true) {
      String input = prompt();
      
      if (input.equalsIgnoreCase("quit")) {
        doQuit();
        break;
      } 
      
      try {
        Command command = (Command) ctx.getBean(input.toLowerCase());
        
        if (!processInterceptor(input, command))
          continue;
        
        command.execute();
        
      } catch (BeansException e) {
        System.out.println("명령어가 올바르지 않습니다.");
      } catch (Exception e) {
        System.out.println("명령어를 처리하는 중에 문제가 발생했습니다. 아빠에게 문의하세요!");
        e.printStackTrace();
      }
    }
    
    ctx.close();
  }
  
  private boolean processInterceptor(String input, Command command) {
    
    String[] interceptorNames = ctx.getBeanNamesForType(CommandInterceptor.class);
    
    
    for (String interceptorName : interceptorNames) {
      CommandInterceptor interceptor = (CommandInterceptor) ctx.getBean(interceptorName);
      if (!interceptor.preHandle(input, command))
        return false;
    }
    return true;
  }

  private void doQuit() {
    System.out.println("종료합니다. 화이팅!");
  }
  
  public String prompt() {
    System.out.print("\n명령> ");
    return keyboard.nextLine();
  }
  
  public static void main(String[] args) throws Exception {
    App app = new App();
    app.service();
  }
}
