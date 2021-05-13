package cn.itcast.travel.dao.impl;

import cn.itcast.travel.domain.User;

/**
 * @Author: chong
 * @Date: 2020-11-14
 */
public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 用户保存
     * @param user
     */
    public void save(User user);

    User findByCode(String code);

    void updateStatus(User user);

    User findByUsernameAndPassword(String username, String password);
}