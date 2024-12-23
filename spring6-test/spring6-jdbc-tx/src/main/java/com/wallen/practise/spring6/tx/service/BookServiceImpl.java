package com.wallen.practise.spring6.tx.service;

import com.wallen.practise.spring6.tx.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Wallen
 * @date 2024/12/11 13:54
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Transactional
    @Override
    public void buyBook(Integer bookId, Integer userId) {
        //查询图书价格
        Integer price = bookDao.getPriceByBookId(bookId);
        //更新图书的库存
        bookDao.updateStock(bookId);
        //更新用户余额
        bookDao.updateBalance(userId, price);
    }
}
