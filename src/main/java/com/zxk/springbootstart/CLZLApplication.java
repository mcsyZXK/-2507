package com.zxk.springbootstart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


//启动类
@ComponentScan(basePackages = "com.zxk")
@SpringBootApplication
@EnableScheduling
public class CLZLApplication {

    public static void main(String[] args) {
        SpringApplication.run(CLZLApplication.class, args);

    }
}
