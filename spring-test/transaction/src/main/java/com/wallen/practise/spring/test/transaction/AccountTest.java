package com.wallen.practise.spring.test.transaction;

import com.wallen.practise.spring.test.transaction.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Wallen
 * @date 2025/4/14 14:43
 */
public class AccountTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService     accountService     = applicationContext.getBean(AccountService.class);
        accountService.transferMoney("tom", "lucy", 500);
    }
}
