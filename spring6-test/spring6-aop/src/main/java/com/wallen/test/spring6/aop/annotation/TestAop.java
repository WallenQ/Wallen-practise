package com.wallen.test.spring6.aop.annotation;

import com.wallen.test.spring6.aop.Calculator;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Wallen
 * @date 2024/11/29 09:45
 */
public class TestAop {

    @Test
    public void testAdd() {
        ClassPathXmlApplicationContext context    = new ClassPathXmlApplicationContext("bean.xml");
        Calculator                     calculator = context.getBean(Calculator.class);
        calculator.add(2,3);
    }
}
