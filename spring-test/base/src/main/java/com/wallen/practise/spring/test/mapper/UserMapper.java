package com.wallen.practise.spring.test.mapper;

import com.wallen.practise.spring.test.bean.User;

import java.util.List;

/**
 * @author Wallen
 * @date 2025/2/27 16:58
 */
public interface UserMapper {
    List<User> findAll();
}
