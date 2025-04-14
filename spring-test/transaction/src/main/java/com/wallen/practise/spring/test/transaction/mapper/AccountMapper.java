package com.wallen.practise.spring.test.transaction.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * @author Wallen
 * @date 2025/4/14 11:55
 */
@Component
public interface AccountMapper {
    /**
     * 加钱
     *
     * @param accountName
     * @param money
     */
    @Update("update tb_account set money=money+#{money} where account_name=#{accountName}")
    void increaseMoney(@Param("accountName") String accountName, @Param("money") Integer money);

    /**
     * 减钱
     *
     * @param accountName
     * @param money
     */
    @Update("update tb_account set money=money-#{money} where account_name=#{accountName}")
    void decreaseMoney(@Param("accountName") String accountName, @Param("money") Integer money);
}
