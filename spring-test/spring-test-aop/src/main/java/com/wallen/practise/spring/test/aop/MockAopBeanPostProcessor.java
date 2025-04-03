package com.wallen.practise.spring.test.aop;

import com.wallen.practise.spring.test.aop.advice.MyAdvice;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Proxy;

/**
 * @author Wallen
 * @date 2025/4/2 11:38
 */
public class MockAopBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        //对UserService中的show1和show2方法进行增强，增强方法在MyAdvice中
        //筛选service.impl包下的所有类的所有方法进行增强
        //Spring容器中获得MyAdvice
        if (bean.getClass().getPackage().getName().equals("com.wallen.practise.spring.test.aop.service.impl")) {
            //生成当前Bean的Proxy对象
            return Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                        MyAdvice myAdvice = applicationContext.getBean(MyAdvice.class);
                        //执行增强对象的before方法
                        myAdvice.beforeAdvice();
                        //执行目标对象的目标方法
                        Object result = method.invoke(bean, args);
                        //执行增强对象的after方法
                        myAdvice.afterAdvice();
                        return result;
                    });
        }
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
