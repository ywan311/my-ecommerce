package com.example.myecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAspectJAutoProxy
@EnableJpaAuditing
@SpringBootApplication
public class MyEcommerceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyEcommerceApplication.class, args);
    }
}
