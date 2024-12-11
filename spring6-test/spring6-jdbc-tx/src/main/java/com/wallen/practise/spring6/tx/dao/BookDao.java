package com.wallen.practise.spring6.tx.dao;

/**
 * @author Wallen
 * @date 2024/12/11 13:53
 */
public interface BookDao {
    Integer getPriceByBookId(Integer bookId);

    void updateStock(Integer bookId);

    void updateBalance(Integer userId, Integer price);
}
