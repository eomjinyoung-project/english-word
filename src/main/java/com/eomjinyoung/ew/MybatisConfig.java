package com.eomjinyoung.ew;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.eomjinyoung.ew.dao") // DAO 인터페이스에 맞춰 프록시를 자동생성시킨다.
public class MybatisConfig {

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext appCtx) 
      throws Exception {
    SqlSessionFactoryBean factory = new SqlSessionFactoryBean();

    // DB 커넥션풀을 관리해주는 객체를 꼽는다.
    factory.setDataSource(dataSource);

    // SQL 맵퍼 파일에서 도메인 객체의 별명을 사용하려면 
    // 도메인 객체가 들어 있는 패키지를 지정해야 한다. 
    // 그러면 Mybatis가 해당 패키지의 모든 클래스에 대해 별명을 자동으로 생성할 것이다.
    factory.setTypeAliasesPackage("com.eomjinyoung.ew.domain");

    // SQL 맵퍼 파일 경로를 등록한다.
    factory.setMapperLocations(appCtx.getResources(
        "classpath:/com/eomjinyoung/ew/mapper/**/*.xml"));
    
    // Mybatis에서 사용하는 로깅 라이브러리를 지정한다.
    org.apache.ibatis.logging.LogFactory.useLog4J2Logging();
    
    return factory.getObject();
  }
}
