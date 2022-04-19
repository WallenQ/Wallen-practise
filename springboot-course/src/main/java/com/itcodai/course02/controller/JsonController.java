package com.itcodai.course02.controller;

import com.itcodai.course02.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试返回json
 *
 * @author Wallen
 * @date 2021年2月19日
 */
@RestController
@RequestMapping("/json")
public class JsonController {

    @RequestMapping("/user")
    public User getUser (){
        return new User(1L,"Wallen","123456");
    }

    @RequestMapping("/list")
    public List<User> getUserList(){
        List<User> userList = new ArrayList<>();
        User user1 = new User(1L,"Wallen","123456");
        User user2 = new User(2L,"Icey","654321");
        userList.add(user1);
        userList.add(user2);
        return userList;
    }

    @RequestMapping("/map")
    public Map<String , Object> getMap(){
        Map<String , Object > map = new HashMap<>(3);
        User user = new User(1L,"Wallen","123456");
        map.put("作者信息",user);
        map.put("博客地址" , "www.baidu.com");
        map.put("CSDN地址" , "www.google.com");
        map.put("粉丝数量" , 4153);
        return map;
    }

    @RequestMapping("/map2")
    public Map<String , Object> getMap2(){
        Map<String , Object > map = new HashMap<>(3);
        User user = new User(1L,"Wallen",null);
        map.put("作者信息",user);
        map.put("博客地址" , "www.baidu.com");
        map.put("CSDN地址" , null);
        map.put("粉丝数量" , 4153);
        return map;
    }
}
