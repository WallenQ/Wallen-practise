package com.wallen.practise.book1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Wallen
 * @date 2024/10/13 16:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String     id;
    private BigDecimal amount;

    @Override
    public String toString() {
        return String.format("Order [id='%s', amount=%4.2f]", id, amount);
    }
}
