package com.teamZero.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.teamZero.app.config", "com.teamZero.app.controller", "com.teamZero.app.service", "com.teamZero.app.dao"})
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

