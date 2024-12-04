package com.wallen.test.spring6.aop.annotation;

import org.aspectj.lang.annotation.Pointcut;

/**
 * @author Wallen
 * @date 2024/12/4 10:40
 */
public class Aspect {
    @Pointcut("execution(public int com.wallen.test.spring6.aop.CalculatorImpl.*(..))")
    public void pointCut(){}
}
