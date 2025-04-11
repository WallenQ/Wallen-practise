package com.wallen.practise.spring.test.aop.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Wallen
 * @date 2025/4/2 11:35
 */
@Component
@Aspect
public class MyAdvice {
    //切点表达式抽取
    @Pointcut("execution(* com.wallen.practise.spring.test.aop.annotation.service.*.*(..))")
    public void myPointcut(){}

    @Before("execution(* com.wallen.practise.spring.test.aop.annotation.service.*.*(..))")
    public void beforeAdvice() {
        System.out.println("before advice...");
    }

    @After("execution(* com.wallen.practise.spring.test.aop.annotation.service.*.*(..))")
    public void afterAdvice() {
        System.out.println("after advice...");
    }

    @AfterReturning("execution(* com.wallen.practise.spring.test.aop.annotation.service.*.*(..))")
    public void afterReturningAdvice() {
        System.out.println("after returning advice...");
    }

    //@Around("execution(* com.wallen.practise.spring.test.aop.annotation.service.*.*(..))")
    @Around("MyAdvice.myPointcut()")
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
    @AfterThrowing(pointcut = "execution(* com.wallen.practise.spring.test.aop.annotation.service.*.*(..))", throwing = "e")
    public void afterThrowingAdvice(Throwable e){
        System.out.println("异常抛出通知。。。");
    }
}
