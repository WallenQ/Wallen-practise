package com.wallen.practise.spring.test.annotation.config;

import com.wallen.practise.spring.test.annotation.service.impl.UserServiceImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

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
public class SpringConfig {
}
