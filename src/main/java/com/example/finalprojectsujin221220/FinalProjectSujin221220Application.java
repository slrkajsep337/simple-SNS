package com.example.finalprojectsujin221220;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FinalProjectSujin221220Application {

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectSujin221220Application.class, args);
    }

}
