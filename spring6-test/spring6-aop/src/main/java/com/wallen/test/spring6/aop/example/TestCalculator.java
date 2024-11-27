package com.wallen.test.spring6.aop.example;

/**
 * @author Wallen
 * @date 2024/11/27 17:09
 */
public class TestCalculator {
    public static void main(String[] args) {
        //创建代理对象
        ProxyFactory proxyFactory = new ProxyFactory(new CalculatorImpl());
        Calculator   proxy        = (Calculator) proxyFactory.getProxy();
        proxy.add(1, 2);
    }
}
