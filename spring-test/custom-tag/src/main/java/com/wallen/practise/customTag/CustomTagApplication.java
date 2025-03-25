package com.wallen.practise.customTag;

import com.wallen.practise.customTag.service.TestService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Wallen
 * @date 2025/3/25 17:16
 */
public class CustomTagApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        TestService                    bean               = applicationContext.getBean(TestService.class);
        System.out.println(bean);
    }
}
