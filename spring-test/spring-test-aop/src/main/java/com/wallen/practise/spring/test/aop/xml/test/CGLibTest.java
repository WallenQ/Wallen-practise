package com.wallen.practise.spring.test.aop.xml.test;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Wallen
 * @date 2025/4/9 17:44
 */
public class CGLibTest {
    public static void main(String[] args) {
        //基于父类生成Proxy
        //目标对象
        Target target = new Target();
        //通知对象
        MyAdvice4 myAdvice4 = new MyAdvice4();
        //编写CGLib代码
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(Target.class);
        //设置回调
        enhancer.setCallback(new MethodInterceptor() {
            /**
             * 相当于JDK的Proxy的invoke方法
             * @param obj
             * @param method
             * @param args
             * @param proxy
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                myAdvice4.before();
                Object invoke = method.invoke(target, args);
                myAdvice4.after();
                return invoke;
            }
        });
        //生成代理对象
        Target proxy = (Target) enhancer.create();
        //测试
        proxy.show();
    }
}
