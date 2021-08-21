package com.example.myecommerce;

import com.example.myecommerce.config.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableConfigurationProperties({FileUploadProperties.class})
@EnableAspectJAutoProxy
@EnableJpaAuditing
@SpringBootApplication
@EnableRedisHttpSession
public class MyEcommerceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyEcommerceApplication.class, args);
    }
}
