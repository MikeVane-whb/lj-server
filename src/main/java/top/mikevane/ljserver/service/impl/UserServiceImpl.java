package top.mikevane.ljserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mikevane.ljserver.mapper.UserMapper;
import top.mikevane.ljserver.pojo.User;
import top.mikevane.ljserver.service.UserService;

/**
 * @author: whb
 * @date: 2022-09-15-15-37
 * @version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User[] selectByUser(User user) {
        return userMapper.selectByUser(user);
    }
}
