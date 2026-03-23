package com.atguigu.gulimall.product.dao;

import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author Wallen
 * @email 872465234@qq.com
 * @date 2022-06-21 23:57:37
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
