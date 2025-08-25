package com.wallen.practise.book1.security.calculator.operation;

import com.wallen.practise.book1.security.calculator.Operation;
import org.springframework.stereotype.Component;

/**
 * @author Wallen
 * @date 2023/1/8 22:20
 */
@Component
public class Multiplication implements Operation {
    @Override
    public int apply(int lhs, int rhs) {
        return lhs * rhs;
    }

    @Override
    public boolean handles(char op) {
        return '*' == op;
    }
}
