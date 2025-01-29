package com.wallen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.awt.print.Book;

/**
 * @author wallen
 * @date 2023/8/27 18:27
 */
@Configuration(proxyBeanMethods = true)
//@ImportResource("applicationContext1.xml")
public class SpringConfig2 {
    @Bean
    public Book book(){
        System.out.println("book init ...");
        return new Book();
    }
}
