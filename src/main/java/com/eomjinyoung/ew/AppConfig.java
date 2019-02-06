package com.eomjinyoung.ew;

import java.util.Scanner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.eomjinyoung.ew")
public class AppConfig {
  
  @Bean
  public Scanner keyboard() {
    return new Scanner(System.in);
  }

  @Bean AppSession appSession() {
    return new AppSession();
  }
}
