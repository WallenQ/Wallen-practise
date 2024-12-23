package com.wallen.practise.spring6.tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Wallen
 * @date 2024/12/24 00:16
 */
@Service
public class CheckoutServiceImpl implements CheckoutService {
    @Autowired
    private BookService bookService;

    /**
     * 一次性购买多本图书
     *
     * @param bookIds
     * @param userId
     */
    @Transactional
    @Override
    public void checkout(Integer[] bookIds, Integer userId) {
        for (Integer bookId : bookIds) {
            bookService.buyBook(bookId, userId);
        }
    }
}
