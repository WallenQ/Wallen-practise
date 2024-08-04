package com.wallen.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wallen
 * @date 2023/8/27 18:19
 */
//@Component
@Configuration
public class DbConfig {
    @Bean
    public DruidDataSource dataSource() {
        return new DruidDataSource();
    }
}
