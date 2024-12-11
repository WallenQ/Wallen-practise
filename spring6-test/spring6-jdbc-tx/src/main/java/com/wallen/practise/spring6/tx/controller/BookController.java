package com.wallen.practise.spring6.tx.controller;

import com.wallen.practise.spring6.tx.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author Wallen
 * @date 2024/12/11 13:53
 */
@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    public void buyBook(Integer bookId, Integer userId) {
        bookService.buyBook(bookId, userId);
    }
}
