package com.wallen.bean;

import org.springframework.stereotype.Component;

/**
 * @author wallen
 * @date 2024/8/5 09:15
 */
@Component
public class CartoonCatAndMouse {
    private Cat cat;
    private Mouse mouse;

    public CartoonCatAndMouse() {
        Cat cat = new Cat();
        cat.setName("tom");
        cat.setAge(3);
        this.cat = cat;
        Mouse mouse = new Mouse();
        mouse.setName("jerry");
        mouse.setAge(4);
        this.mouse = mouse;
    }

    public void play() {
        System.out.println(cat.getAge() + "岁的" + cat.getName() + "和" + mouse.getAge() + "岁的" + mouse.getName() + "打起来了");
    }
}
