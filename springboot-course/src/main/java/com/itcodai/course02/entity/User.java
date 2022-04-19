package com.itcodai.course02.entity;

import lombok.Data;

/**
 * 测试实体类
 *
 * @author Wallen
 * @date 2021年2月19日
 */
@Data
public class User {
    private Long id;
    private String userName;
    private String password;

    public User(Long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }
}
