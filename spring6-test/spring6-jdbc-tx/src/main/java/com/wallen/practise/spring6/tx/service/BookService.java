package com.wallen.practise.spring6.tx.service;

/**
 * @author Wallen
 * @date 2024/12/11 13:54
 */
public interface BookService {
    void buyBook(Integer bookId, Integer userId);
}
