package com.wallen.test.spring.spring6.ioc.xml.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Wallen
 * @date 2024/10/24 16:45
 */
public class TestJdbc {
    @Test
    public void demo2() {
        ApplicationContext context    = new ClassPathXmlApplicationContext("bean-jdbc.xml");
        DruidDataSource    dataSource = context.getBean("druidDataSource", DruidDataSource.class);
        System.out.println(dataSource.getUrl());
    }

    @Test
    public void demo1() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/dncity?serverTimezone=UTC-8");
        dataSource.setUsername("root");
        dataSource.setPassword("12345678");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    }
}
