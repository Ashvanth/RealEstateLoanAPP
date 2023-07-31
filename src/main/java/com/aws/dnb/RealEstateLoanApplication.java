package com.aws.dnb;

import com.aws.dnb.dao.ApplicantStoreRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.aws.dnb")
public class RealEstateLoanApplication {
    public static void main(String[] args) {
        SpringApplication.run(RealEstateLoanApplication.class, args);
    }

}