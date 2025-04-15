package com.wallen.practise.spring.test.transaction.service.impl;

import com.wallen.practise.spring.test.transaction.mapper.AccountMapper;
import com.wallen.practise.spring.test.transaction.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Wallen
 * @date 2025/4/14 14:34
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void transferMoney(String outAccount, String inAccount, Integer money) {
        accountMapper.decreaseMoney(outAccount, money);
        int i = 1/0;
        accountMapper.increaseMoney(inAccount, money);
    }
}
