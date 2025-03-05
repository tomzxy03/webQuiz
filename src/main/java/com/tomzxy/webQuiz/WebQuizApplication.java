package com.tomzxy.webQuiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.tomzxy.webQuiz.repository")
public class WebQuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebQuizApplication.class, args);
	}

}
