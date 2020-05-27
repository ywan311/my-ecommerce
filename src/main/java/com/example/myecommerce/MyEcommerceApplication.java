package com.example.myecommerce;

import com.example.myecommerce.config.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableConfigurationProperties({FileUploadProperties.class})
@EnableJpaAuditing
@SpringBootApplication
@EnableWebMvc
public class MyEcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyEcommerceApplication.class, args);
    }

}
