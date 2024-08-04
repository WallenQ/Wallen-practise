package com.wallen.config;

import com.wallen.bean.Cat;
import com.wallen.bean.Mouse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * @author wallen
 * @date 2024/8/4 11:38
 */
@Import(Mouse.class)
public class SpringConfig10 {
    @Bean
    @ConditionalOnBean(name = "Jerry")
    //@ConditionalOnClass(name = "com.wallen.bean.Mouse")
    //@ConditionalOnMissingClass("com.wallen.bean.Wolf")
    public Cat tom (){
        return new Cat();
    }
}
