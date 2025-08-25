package com.wallen.practise.book1.security;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * @author Wallen
 * @date 2022/1/11 12:37
 */
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
//@SpringBootApplication
public class DemoApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class,args);

        System.out.println("# Beans:" + ctx.getBeanDefinitionCount());

        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanDefinitionNames);
        Arrays.asList(beanDefinitionNames).forEach(System.out::println);

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        WebMvcConfigurer.super.configureAsyncSupport(configurer);
    }

    @Bean
    public ThreadPoolTaskExecutor mvcTaskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setThreadNamePrefix("wallen-mvc-task-");
        return taskExecutor;
    }
}
