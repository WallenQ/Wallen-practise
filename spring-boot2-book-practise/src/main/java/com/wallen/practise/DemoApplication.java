package com.wallen.practise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author Wallen
 * @date 2022/1/11 12:37
 */
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class,args);

        System.out.println("# Beans:" + ctx.getBeanDefinitionCount());

        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanDefinitionNames);
        Arrays.asList(beanDefinitionNames).forEach(System.out::println);

    }
}
