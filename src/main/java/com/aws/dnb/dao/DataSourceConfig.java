package com.aws.dnb.dao;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://database-1.cub9byjgpr1p.eu-west-1.rds.amazonaws.com:3306/LOAN");
        dataSourceBuilder.username("admin");
        dataSourceBuilder.password("password");
        return dataSourceBuilder.build();
    }
}
