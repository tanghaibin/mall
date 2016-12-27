package top.tanghaibin.mall.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tanghaibin.mall.manager.mapper.UserMapper;
import top.tanghaibin.mall.manager.pojo.User;
import top.tanghaibin.mall.manager.service.UserService;

/**
 * Created by tanghaibin on 2016/12/27.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public void create(User user) {
        userMapper.insert(user);
    }

    public User search(User user) {
        return userMapper.selectByPrimaryKey(user);
    }

    public void delete(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }
}
