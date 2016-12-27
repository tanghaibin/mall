package top.tanghaibin.mall.manager.service;

import top.tanghaibin.mall.manager.pojo.User;

/**
 * Created by tanghaibin on 2016/12/27.
 */
public interface UserService {

    void create(User user);

    User search(User user);

    void delete(Integer id);
}
