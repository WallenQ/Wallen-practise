package com.wallen.practise.spring6.jdbc;

import com.wallen.practise.spring6.jdbc.po.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

/**
 * @author Wallen
 * @date 2024/12/6 14:48
 */
@SpringJUnitConfig(locations = "classpath:bean.xml")
public class JdbcTemplateTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testUpdate() {
        //添加
        String insertSql = "insert into t_emp values(null, ?, ?, ?)";
        jdbcTemplate.update(insertSql, "用户3", 22, "男");

        //修改
        //String updateSql = "update t_emp set name = ? where id = ?";
        //jdbcTemplate.update(updateSql, "用户2", 1);

        //删除
        //String deleteSql = "delete from t_emp where id = ?";
        //jdbcTemplate.update(deleteSql, 1);
    }

    @Test
    public void testSelectObject() {
        String sql = "select * from t_emp where id = ?";
        Emp    emp = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Emp.class), 2);
        System.out.println(emp);
    }

    @Test
    public void testSelectList() {
        String    sql    = "select * from t_emp";
        List<Emp> result = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Emp.class));
        System.out.println(result);
    }

    @Test
    public void selectCount() {
        String  sql   = "select count(*) from t_emp";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(count);
    }
}
