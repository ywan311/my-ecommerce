package com.example.myecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MyEcommerceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyEcommerceApplication.class, args);

    }
}
