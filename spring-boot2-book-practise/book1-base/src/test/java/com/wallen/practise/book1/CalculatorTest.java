package com.wallen.practise.book1;

import com.wallen.practise.book1.calculator.Calculator;
import com.wallen.practise.book1.calculator.Operation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Wallen
 * @date 2024/9/16 19:11
 */
public class CalculatorTest {
    private Calculator calculator;

    private Operation mockOperation;

    @Before
    public void setup() {
        mockOperation = Mockito.mock(Operation.class);
        calculator = new Calculator(Collections.singleton(mockOperation));
    }

    @Test
    public void throwExceptionWhenNoSuitableOperationFound() {
        when(mockOperation.handles(anyChar())).thenReturn(false);
        calculator.calculator(2, 2, '*');
    }

    @Test
    public void shouldCallApplyMethodWhenSuitableOperationFound() {
        when(mockOperation.handles(anyChar())).thenReturn(true);
        when(mockOperation.apply(2, 2)).thenReturn(4);
        calculator.calculator(2, 2, '*');

        verify(mockOperation, times(1)).apply(2, 2);
    }
}
