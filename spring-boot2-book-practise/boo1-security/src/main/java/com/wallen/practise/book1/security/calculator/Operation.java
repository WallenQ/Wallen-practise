package com.wallen.practise.book1.security.calculator;

/**
 * @author Wallen
 * @date 2023/1/8 22:09
 */
public interface Operation {

    int apply(int lhs, int rhs);

    boolean handles(char op);
}
