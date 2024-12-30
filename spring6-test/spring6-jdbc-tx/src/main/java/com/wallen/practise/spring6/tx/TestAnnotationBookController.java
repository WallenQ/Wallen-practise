package com.wallen.practise.spring6.tx;

import com.wallen.practise.spring6.tx.config.SpringConfig;
import com.wallen.practise.spring6.tx.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Wallen
 * @date 2024/12/30 11:23
 */
public class TestAnnotationBookController {

    @Test
    public void checkout(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookService                        bookService               = applicationContext.getBean(BookService.class);
        bookService.buyBook(1,1);
    }
}
