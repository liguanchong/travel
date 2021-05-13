package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

/**
 * @Author: chong
 * @Date: 2020-11-17
 */
public interface RouteImgDao {
    public List<RouteImg> findByRid(int rid);
}
