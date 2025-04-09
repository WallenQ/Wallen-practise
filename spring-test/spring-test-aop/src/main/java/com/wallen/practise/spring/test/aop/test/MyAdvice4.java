package com.wallen.practise.spring.test.aop.test;

/**
 * @author Wallen
 * @date 2025/4/9 17:44
 */
public class MyAdvice4 {
    public void before() {
        System.out.println("before advice...");
    }

    public void after() {
        System.out.println("after advice...");
    }
}
