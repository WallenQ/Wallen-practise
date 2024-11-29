package com.wallen.test.spring6.aop.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 切面
 *
 * @author Wallen
 * @date 2024/11/29 09:30
 */
@Aspect
@Component
public class LogAspect {
    //设置切入点和通知类型

    //通知类型：
    // 前置
    @Before(value = "execution(public int com.wallen.test.spring6.aop.CalculatorImpl.*(..))")
    public void before(){
        System.out.println("Logger-->前置通知");
    }
    // 返回
    // 异常
    // 后置
    // 环绕
}
