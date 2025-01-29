package com.wallen;

import com.wallen.bean.CartoonCatAndMouse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * @author wallen
 * @date 2024/8/4 11:16
 */
@SpringBootApplication
@Import(CartoonCatAndMouse.class)
public class App12 {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(App12.class);
        CartoonCatAndMouse bean = ctx.getBean(CartoonCatAndMouse.class);
        bean.play();
    }
}
