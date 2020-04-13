package com.xyz.bit.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class CommDBConfig {

  @Value("${spring.database.driverClassName}")
  private String dbDriverClassName;

  @Value("${spring.datasource.url}")
  private String dbUrl;

  @Value("${spring.datasource.username}")
  private String dbUsername;

  @Value("${spring.datasource.password}")
  private String dbPassword;

  public DataSource getDriverManagerDataSource() {
    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
    driverManagerDataSource.setDriverClassName(dbDriverClassName);
    driverManagerDataSource.setUrl(dbUrl);
    driverManagerDataSource.setUsername(dbUsername);
    driverManagerDataSource.setPassword(dbPassword);
    return driverManagerDataSource;
  }

  public JdbcTemplate getJdbcTemplate() {
    return new JdbcTemplate(getDriverManagerDataSource());
  }


}
