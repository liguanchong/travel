package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.Seller;

public interface SellerDao {
    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Seller findById(int id);
}
