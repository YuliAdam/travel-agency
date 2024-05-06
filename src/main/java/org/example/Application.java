package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan("org.example.*")
public class Application {
    public static void main(String[] args) {
    SpringApplication.run(Application.class,args);
    }

}