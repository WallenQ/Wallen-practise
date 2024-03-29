package com.wallen.practise.calculator.operation;

import com.wallen.practise.calculator.Operation;
import org.springframework.stereotype.Component;

/**
 * @author Wallen
 * @date 2023/1/8 22:19
 */
@Component
public class Addition implements Operation {
    @Override
    public int apply(int lhs, int rhs) {
        return lhs + rhs;
    }

    @Override
    public boolean handles(char op) {
        return '+' == op;
    }
}
