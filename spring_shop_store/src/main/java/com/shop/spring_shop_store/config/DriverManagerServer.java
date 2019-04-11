package com.shop.spring_shop_store.config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DriverManagerServer {
    public static DriverManagerDataSource getDataSource() {
        DriverManagerDataSource managerDataSource = new DriverManagerDataSource();
        managerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        managerDataSource.setUrl("jdbc:mysql://localhost:8080/server?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        managerDataSource.setUsername("root");
        managerDataSource.setPassword("root");
        return managerDataSource;
    }
}
