package com.wallen.config;

import com.wallen.bean.DogFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wallen
 * @date 2023/8/27 18:27
 */
@ComponentScan({"com.wallen.config", "com.wallen.bean"})
public class SpringConfig {

    @Bean
    public DogFactoryBean dog() {
        return new DogFactoryBean();
    }
}

