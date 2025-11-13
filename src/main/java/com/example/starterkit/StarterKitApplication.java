package com.example.starterkit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableJpaAuditing
@EnableAspectJAutoProxy
public class StarterKitApplication {
    public static void main(String[] args) {
        SpringApplication.run(StarterKitApplication.class, args);
    }
}
