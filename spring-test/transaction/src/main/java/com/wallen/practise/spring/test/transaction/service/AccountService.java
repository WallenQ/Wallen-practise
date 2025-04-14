package com.wallen.practise.spring.test.transaction.service;

/**
 * @author Wallen
 * @date 2025/4/14 14:33
 */
public interface AccountService {
    void transferMoney(String outAccount, String inAccount, Integer money);
}
