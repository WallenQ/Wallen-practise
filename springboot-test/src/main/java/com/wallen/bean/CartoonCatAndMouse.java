package com.wallen.bean;

import com.alibaba.druid.util.StringUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wallen
 * @date 2024/8/5 09:15
 */
@Data
@EnableConfigurationProperties(CartoonProperties.class)
public class CartoonCatAndMouse {
    private Cat cat;
    private Mouse mouse;
    private CartoonProperties cartoonProperties;

    public CartoonCatAndMouse(CartoonProperties cartoonProperties) {
        this.cartoonProperties = cartoonProperties;
        if (cartoonProperties.getCat()!= null){
            cat = new Cat();
            cat.setName(cartoonProperties.getCat().getName());
            cat.setAge(cartoonProperties.getCat().getAge());
        }
        if (cartoonProperties.getMouse()!= null){
            mouse = new Mouse();
            mouse.setName(cartoonProperties.getMouse().getName());
            mouse.setAge(cartoonProperties.getMouse().getAge());
        }
    }
    /*public CartoonCatAndMouse() {
        Cat cat = new Cat();
        cat.setName("tom");
        cat.setAge(3);
        this.cat = cat;
        Mouse mouse = new Mouse();
        mouse.setName("jerry");
        mouse.setAge(4);
        this.mouse = mouse;
    }*/

    public void play() {
        System.out.println(cat.getAge() + "岁的" + cat.getName() + "和" + mouse.getAge() + "岁的" + mouse.getName() + "打起来了");
    }
}
