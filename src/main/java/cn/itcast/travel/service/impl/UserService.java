package cn.itcast.travel.service.impl;

import cn.itcast.travel.domain.User;

/**
 * @Author: chong
 * @Date: 2020-11-14
 */
public interface UserService {
    boolean regist(User user);

    boolean active(String code);

    User login(User user);
}
