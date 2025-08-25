package com.wallen.practise.spring.test.processor;

import com.wallen.practise.spring.test.util.BaseClassScanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.util.Map;

/**
 * @author Wallen
 * @date 2025/2/10 10:05
 */
public class MyComponentBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        //通过扫描工具扫描指定包及其子包下所有类，收集使用@MyComponent注解的类
        Map<String, Class> myComponentAnnotationMap = BaseClassScanUtils.scanMyComponentAnnotation("com.wallen");
        //遍历map，将类注册到spring容器中
        myComponentAnnotationMap.forEach((beanName, clazz) -> {
            //获得beanClassName
            String beanClassName = clazz.getName();
            //创建BeanDefinition对象
            BeanDefinition beanDefinition = new RootBeanDefinition();
            beanDefinition.setBeanClassName(beanClassName);
            //注册
            registry.registerBeanDefinition(beanName, beanDefinition);
        });
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //BeanDefinitionRegistryPostProcessor.super.postProcessBeanFactory(beanFactory);
    }
}
