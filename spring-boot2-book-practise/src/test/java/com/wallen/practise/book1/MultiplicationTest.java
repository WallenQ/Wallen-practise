package com.wallen.practise.book1;

import com.wallen.practise.book1.calculator.operation.Multiplication;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author wallen
 * @date 2024/4/9 11:41
 */
public class MultiplicationTest {
    private final Multiplication addition = new Multiplication();

    @Test
    public void shouldMatchOperation() {
        assertThat(addition.handles('*')).isTrue();
        assertThat(addition.handles('/')).isTrue();
    }

    @Test
    public void shouldCorrectlyApplyFormula() {
        assertThat(addition.apply(2,2)).isEqualTo(4);
        assertThat(addition.apply(12,10)).isEqualTo(120);
    }
}
