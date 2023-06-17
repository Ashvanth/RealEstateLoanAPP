package com.aws.dnb;

import com.aws.dnb.controller.CalculatorController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CalculaterApp {
    public static void main(String[] args) {
        SpringApplication.run(CalculaterApp.class, args);
    }

    @Bean
    public CalculatorController calculatorController() {
        return new CalculatorController();
    }
}