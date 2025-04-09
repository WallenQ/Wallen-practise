package com.wallen.practise.spring.test.aop.xml.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author Wallen
 * @date 2025/4/2 11:35
 */
public class MyAdvice2 implements MethodBeforeAdvice, AfterReturningAdvice, MethodInterceptor {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("before advice...");
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("after returning advice...");
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        //环绕目标方法
        System.out.println("环绕前增强。。。");
        //执行目标方法
        Object result = invocation.getMethod().invoke(invocation.getThis(), invocation.getArguments());
        System.out.println("环绕后增强。。。");
        return result;
    }
}
