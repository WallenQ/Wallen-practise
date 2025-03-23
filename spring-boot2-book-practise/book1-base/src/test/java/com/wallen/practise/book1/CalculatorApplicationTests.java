package com.wallen.practise.book1;

import com.wallen.practise.book1.calculator.Calculator;
import com.wallen.practise.book1.calculator.CalculatorApplication;
import com.wallen.practise.book1.calculator.Operation;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.system.OutputCaptureRule;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Wallen
 * @date 2024/9/16 19:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CalculatorApplication.class)
public class CalculatorApplicationTests {
    @Autowired
    private Calculator calculator;
    @Rule
    public OutputCaptureRule capture = new OutputCaptureRule();

    @Test
    public void doingMultiplicationShouldSucceed() {
        calculator.calculator(12, 13, '*');
        capture.expect(Matchers.containsString("12 * 13 = 156"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void doingDivisionShouldFail() {
        calculator.calculator(12, 13, '/');
    }

    @MockBean(name = "division")
    private Operation moctOperation;

    @Test
    public void calculatorShouldHave3Operations() {
        Object operations = ReflectionTestUtils.getField(calculator, "operations");

        assertThat((Collection) operations).hasSize(3);
    }

    @Test
    public void mockDivision(){
        when(moctOperation.handles('/')).thenReturn(true);
        when(moctOperation.apply(14,7)).thenReturn(2);

        calculator.calculator(14,7,'/');
        capture.expect(Matchers.containsString("14 / 7 = 2"));
    }
}
