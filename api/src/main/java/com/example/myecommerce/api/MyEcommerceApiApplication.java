package com.example.myecommerce.api;

import com.example.myecommerce.api.config.FileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@ComponentScan(basePackages = {
        "com.example.myecommerce.api", "com.example.myecoomerce.myecommercecore"
})
@EntityScan(basePackages = "com.example.myecoomerce.myecommercecore")
@EnableJpaRepositories(basePackages = "com.example.myecoomerce.myecommercecore")
@EnableConfigurationProperties({FileUploadProperties.class})
@EnableAspectJAutoProxy
@EnableJpaAuditing
@SpringBootApplication
@EnableRedisHttpSession
public class MyEcommerceApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyEcommerceApiApplication.class, args);
    }
}
