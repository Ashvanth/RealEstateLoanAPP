package com.aws.dnb;

import com.aws.dnb.controller.LoanController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RealEstateLoanApplication {
    public static void main(String[] args) {
        SpringApplication.run(RealEstateLoanApplication.class, args);
    }

}