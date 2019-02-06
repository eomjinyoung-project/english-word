package com.eomjinyoung.ew;

import java.io.File;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import com.eomjinyoung.ew.service.WordService;


@ComponentScan("com.eomjinyoung.ew")
public class DataUploadApp {

  public static void main(String[] args) throws Exception {
    AnnotationConfigApplicationContext ctx = 
        new AnnotationConfigApplicationContext(AppConfig.class);
    WordService wordService = (WordService) ctx.getBean(WordService.class);
    //wordService.uploadFromExcelToDatabase(new File("./data/english-word-1600.xlsx"));
    wordService.uploadFromExcelToDatabase(new File("./data/word-max-prime-highschool-1800.xlsx"));
    ctx.close();
  }
}
