package com.yw.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yaowei
 * 2019-08-28
 */
@MapperScan("com.yw.dao")
@ComponentScan(basePackages = "com.yw")
//@ServletComponentScan(basePackages = "com.yw")
@SpringBootApplication
public class StartApplication {
  public static void main(String[] args) {
    SpringApplication.run(StartApplication.class, args);
  }
}
