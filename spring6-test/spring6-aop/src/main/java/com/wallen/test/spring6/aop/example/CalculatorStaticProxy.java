package com.wallen.test.spring6.aop.example;

import com.wallen.test.spring6.aop.Calculator;

/**
 * @author Wallen
 * @date 2024/11/26 19:44
 */
public class CalculatorStaticProxy implements Calculator {
    //被代理目标对象传递过来
    private Calculator calculator;

    public CalculatorStaticProxy(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public int add(int i, int j) {
        System.out.println("[日志] add 方法开始了， 参数是：" + i + "," + j);
        int result = calculator.add(i, j);
        System.out.println("[日志] add 方法结束了， 结果是：" + result);
        return result;
    }

    @Override
    public int sub(int i, int j) {
        System.out.println("[日志] sub 方法开始了， 参数是：" + i + "," + j);
        int result = calculator.sub(i, j);
        System.out.println("[日志] sub 方法结束了， 结果是：" + result);
        return result;
    }

    @Override
    public int mul(int i, int j) {
        System.out.println("[日志] mul 方法开始了， 参数是：" + i + "," + j);
        int result = calculator.mul(i, j);
        System.out.println("[日志] mul 方法结束了， 结果是：" + result);
        return result;
    }

    @Override
    public int div(int i, int j) {
        System.out.println("[日志] div 方法开始了， 参数是：" + i + "," + j);
        int result = calculator.div(i, j);
        System.out.println("[日志] div 方法结束了， 结果是：" + result);
        return result;
    }
}
