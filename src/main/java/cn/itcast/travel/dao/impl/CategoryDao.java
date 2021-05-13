package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @Author: 李冠冲
 * @Date: 2020-11-15
 */
public interface CategoryDao {
    public List<Category> findAll();
}
