package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

/**
 * @Author: chong
 * @Date: 2020-11-14
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean regist(User user) {
        User u = userDao.findByUsername(user.getUsername());
        if(u != null){
            //用户名存在，注册失败
            return false;
        }
        //2.保存用户信息
        //2.1设置激活码，唯一字符串
        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");
        userDao.save(user);
        String content="<a href='http://localhost/travel/user/active?code="+user.getCode()+"'>点击激活【东软旅游网】</a>";
        MailUtils.sendMail(user.getEmail(), content, "激活邮件");
        return true;
    }

    @Override
    public boolean active(String code) {
        User user = userDao.findByCode(code);
        if (user != null) {
            userDao.updateStatus(user);
            return true;
        } else {

            return false;
        }
    }

    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }


}
