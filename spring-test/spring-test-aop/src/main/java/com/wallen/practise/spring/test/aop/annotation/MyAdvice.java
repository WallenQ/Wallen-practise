package com.wallen.practise.spring.test.aop.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author Wallen
 * @date 2025/4/2 11:35
 */
@Component
@Aspect
public class MyAdvice {
    @Before("execution(* com.wallen.practise.spring.test.aop.annotation.service.*.*(..))")
    public void beforeAdvice() {
        System.out.println("before advice...");
    }

    public void afterAdvice() {
        System.out.println("after advice...");
    }

    public void afterReturningAdvice() {
        System.out.println("after returning advice...");
    }

    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //环绕目标方法
        System.out.println("环绕前增强。。。");
        //执行目标方法
        Object result = proceedingJoinPoint.proceed();
        System.out.println("环绕后增强。。。");
        return result;
    }

    /**
     * 报异常才通知
     */
    public void afterThrowingAdvice(){
        System.out.println("异常抛出通知。。。");
    }
}
