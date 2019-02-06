package com.eomjinyoung.ew;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource({"classpath:jdbc.properties", "classpath:security/jdbc.properties"})
@EnableTransactionManagement  //트랜잭션 관리자를 활성화시킨다.
public class DataSourceConfig {
  
  @Autowired Environment env;
  
  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName(env.getProperty("jdbc.driver"));
    ds.setUrl(env.getProperty("jdbc.url"));
    ds.setUsername(env.getProperty("jdbc.username"));
    ds.setPassword(env.getProperty("jdbc.password"));
    return ds;
  }
  
  
  // 트랜잭션 관리자의 이름은 반드시 "transactionManager"이어야 한다.
  // 그래서 메서드 이름을 다음과 같이 지은 것이다.
  // Spring에서 트랜잭션 관리자를 찾을 때 이 이름으로 찾는다.
  // 만약 트랜잭션 이름을 다른 이름을 지었다면 
  // 트랜잭션 관리 설정에서 그 이름을 알려줘야 한다.
  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    // 트랜잭션 관리자가 하는 일은 DB 커넥션의 commit과 rollback을 다루는 것이다.
    // 따라서 트랜잭션 관리자는 DB 커넥션을 제공해주는 
    // DataSource(DB 커넥션 풀)가 필요하다.
    // 그래서 트랜잭션 관리자를 만들 때 반드시 DataSource 객체를 넘겨줘야 한다.
    // 물론 관리자 객체를 만든 후에 세터를 호출해서 넘겨줘도 된다.
    PlatformTransactionManager txManager = new DataSourceTransactionManager(dataSource);
    
    return txManager;
  }
}
