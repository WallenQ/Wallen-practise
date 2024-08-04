package com.wallen.practise.book1.calculator;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Wallen
 * @date 2023/1/8 22:22
 */
@SpringBootApplication
public class CalculatorApplication1 {
    public static void main(String[] args) {
        SpringApplication.run(CalculatorApplication1.class, args);
    }

    @Bean
    public ApplicationRunner calculationRunner(Calculator calculator) {
        return args -> {
            calculator.calculator(137, 21, '+');
            calculator.calculator(137, 21, '*');
            //calculator.calculator(137, 21, '-');
        };
    }
}
