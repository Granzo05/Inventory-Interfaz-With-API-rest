package org.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("org.example.entities")
@ComponentScan(basePackages = {"org.example.controller", "org.example.repository", "org.example.configuration"})

public class ApiRestApplication {    public static void main(String[] args) {
        SpringApplication.run(ApiRestApplication.class, args);
    }

}