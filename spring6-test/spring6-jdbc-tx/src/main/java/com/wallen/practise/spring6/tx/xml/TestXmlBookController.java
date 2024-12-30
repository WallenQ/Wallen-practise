package com.wallen.practise.spring6.tx.xml;

import com.wallen.practise.spring6.tx.controller.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author Wallen
 * @date 2024/12/11 14:50
 */
@SpringJUnitConfig(locations = "classpath:bean-xml.xml")
public class TestXmlBookController {
    @Autowired
    private BookController bookController;

    @Test
    public void testBuyBook() {
        bookController.buyBook(1, 1);
    }

    @Test
    public void checkout(){
        Integer[] bookIds = {1,2};
        bookController.checkout(bookIds,1);
    }
}
