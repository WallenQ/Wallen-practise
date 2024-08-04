package com.wallen.bean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

/**
 * @author wallen
 * @date 2024/8/4 12:59
 */
@Component("tom1")
@ConditionalOnBean(name = "Jerry")
//@ConditionalOnClass(name = "com.wallen.bean.Mouse")
//@ConditionalOnMissingClass("com.wallen.bean.Wolf")
public class Cat1 {
}
