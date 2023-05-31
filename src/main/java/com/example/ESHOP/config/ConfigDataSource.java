package com.example.ESHOP.config;


import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ConfigDataSource {
    @Bean
    public static DataSource dataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();

        dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/eshop");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("Heslo123!");

        return dataSourceBuilder.build();
    }
}
