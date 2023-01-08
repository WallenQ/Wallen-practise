package com.wallen.practise.calculator;

import java.util.Collection;

/**
 * @author Wallen
 * @date 2023/1/8 22:08
 */
public class Calculator {
    private final Collection<Operation> operations;


    public Calculator(Collection<Operation> operations) {
        this.operations = operations;
    }

    public void calculator(int lhs, int rhs, char op) {
        for (Operation operation : operations) {
            if (operation.handles(op))  {
                int result = operation.apply(lhs, rhs);
                System.out.printf("%d %s %d = %s%n",lhs,op,rhs,result);
                return;
            }
        }
        throw new IllegalArgumentException("Unknown operation " + op);
    }
}
