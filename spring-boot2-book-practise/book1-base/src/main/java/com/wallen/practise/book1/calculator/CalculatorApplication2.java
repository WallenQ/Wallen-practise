package com.wallen.practise.book1.calculator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Wallen
 * @date 2023/1/8 22:22
 */
@SpringBootApplication
public class CalculatorApplication2 {
    public static void main(String[] args) {
        SpringApplication.run(CalculatorApplication2.class, args);
    }

    @Bean
    public ApplicationRunner calculationRunner2(Calculator calculator, @Value("${lhs}") int lhs,
                                               @Value("${rhs}") int rhs, @Value("${op}") char op) {
        return args -> calculator.calculator(lhs, rhs, op);
    }
}
