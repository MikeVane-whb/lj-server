package top.mikevane.ljserver.service;

import top.mikevane.ljserver.pojo.User;

/**
 * @author: whb
 * @date: 2022-09-15-15-37
 * @version: 1.0
 */
public interface UserService {
    /**
     * 根据传入User查询用户信息
     * @param user 用户id/email
     * @return
     */
    User selectByUser(User user);
}
