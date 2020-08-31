package com.yi.demo.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaAuditing
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.yi.demo.sample.repository")
@SpringBootApplication
public class DemoSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSampleApplication.class, args);
    }

}
