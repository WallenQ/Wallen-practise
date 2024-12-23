package com.wallen.practise.spring6.tx;

import com.wallen.practise.spring6.tx.controller.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author Wallen
 * @date 2024/12/11 14:50
 */
@SpringJUnitConfig(locations = "classpath:bean.xml")
public class TestBookController {
    @Autowired
    private BookController bookController;

    @Test
    public void testBuyBook() {
        bookController.buyBook(1, 1);
    }
}