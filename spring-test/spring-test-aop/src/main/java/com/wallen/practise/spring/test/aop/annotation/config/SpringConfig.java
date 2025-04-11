package com.wallen.practise.spring.test.aop.annotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Wallen
 * @date 2025/4/11 17:04
 */
@Configuration
@ComponentScan("com.wallen.practise.spring.test.aop")
@EnableAspectJAutoProxy
public class SpringConfig {
}
