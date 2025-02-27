package com.wallen.practise;

import org.apache.ibatis.io.Resources; // 添加显式导入
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.wallen.practise.spring.test.mapper.UserMapper;

import java.io.InputStream;

/**
 * @author Wallen
 * @date 2025/2/27 17:34
 */
public class MybatisTest {
    public static void main(String[] args) throws Exception {
        InputStream in         = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSession  sqlSession = new SqlSessionFactoryBuilder().build(in).openSession();
        UserMapper  userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.findAll().forEach(System.out::println);
        sqlSession.close();
    }
}
