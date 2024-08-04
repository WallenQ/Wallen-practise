package com.wallen.config;

import com.wallen.bean.Dog;
import org.springframework.context.annotation.Import;

import java.awt.print.Book;

/**
 * @author wallen
 * @date 2023/8/27 18:27
 */
@Import({Dog.class, DbConfig.class})
public class SpringConfig4 {

}
