package com.wallen.test.spring.spring6.ioc.xml.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author Wallen
 * @date 2024/10/25 14:16
 */
public class MyBeanPost implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("3.bean后置处理器，初始化之前执行");
        System.out.println(beanName + ":" + bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("5.bean后置处理器，初始化之后执行");
        System.out.println(beanName + ":" + bean);
        return bean;
    }
}
