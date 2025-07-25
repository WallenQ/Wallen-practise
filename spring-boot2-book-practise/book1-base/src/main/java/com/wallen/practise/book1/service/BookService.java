package com.wallen.practise.book1.service;

import com.wallen.practise.book1.entity.Book;

import java.util.Optional;

/**
 * @Author qianwenlong
 * @Date 2025/7/25 9:21
 */
public interface BookService {
    Iterable<Book> findAll();

    Book create(Book book);

    Optional<Book> find(String isbn);
}
