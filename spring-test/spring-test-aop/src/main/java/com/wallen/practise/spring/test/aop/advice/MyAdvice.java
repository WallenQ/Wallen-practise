package com.wallen.practise.spring.test.aop.advice;

/**
 * @author Wallen
 * @date 2025/4/2 11:35
 */
public class MyAdvice {
    public void beforeAdvice() {
        System.out.println("before advice...");
    }
    public void afterAdvice() {
        System.out.println("after advice...");
    }
}
