package com.zxk.springbootstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AsyncConfig {

    @Bean
    public ExecutorService asyncExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }
}
