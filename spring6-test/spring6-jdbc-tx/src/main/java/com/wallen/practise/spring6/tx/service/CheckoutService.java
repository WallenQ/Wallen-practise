package com.wallen.practise.spring6.tx.service;

/**
 * @author Wallen
 * @date 2024/12/24 00:15
 */
public interface CheckoutService {
    void checkout(Integer[] bookIds, Integer userId);
}
