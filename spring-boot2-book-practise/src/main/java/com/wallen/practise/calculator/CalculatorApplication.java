package com.wallen.practise.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * @author Wallen
 * @date 2023/1/8 22:22
 */
@SpringBootApplication
public class CalculatorApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(CalculatorApplication.class);
        Calculator calculator = ctx.getBean(Calculator.class);

        calculator.calculator(137, 21, '+');
        calculator.calculator(137, 21, '*');
        calculator.calculator(137, 21, '-');
    }

    @Bean
    public Calculator calculator(Collection<Operation> operations) {
        return new Calculator(operations);
    }
}
