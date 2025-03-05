package com.tomzxy.webQuiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {
    @Bean(name = "taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);      // Số thread tối thiểu
        executor.setMaxPoolSize(10);       // Số thread tối đa
        executor.setQueueCapacity(100);    // Số task tối đa trong hàng đợi
        executor.setThreadNamePrefix("Async-Thread-"); // Tiền tố tên thread
        executor.initialize();             // Khởi tạo thread pool
        return executor;
    }
}
