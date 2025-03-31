package com.wallen.practise.spring.test.annotation.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.wallen.practise.spring.test.annotation.service.impl.UserServiceImpl;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * @author Wallen
 * @date 2025/3/28 11:20
 */
@Configuration
//<context:component-scan base-package="com.wallen"/>
@ComponentScan(basePackages = "com.wallen.practise.spring.test.annotation")
//<context:property-placeholder location="classpath:jdbc.properties"/>
@PropertySource("classpath:jdbc.properties")
@Import(UserServiceImpl.class)
@MapperScan("com.wallen.practise")
public class SpringConfig {

    @Bean
    public DataSource dataSource(@Value("${jdbc.driver}") String driver, @Value("${jdbc.url}") String url,
                                 @Value("${jdbc.username}") String username, @Value("${jdbc.password}") String password) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean getSqlSessionBean(DataSource dataSource) {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        return sessionFactoryBean;
    }
}
