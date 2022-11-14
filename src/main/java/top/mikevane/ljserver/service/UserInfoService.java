package top.mikevane.ljserver.service;

import org.springframework.stereotype.Service;
import top.mikevane.ljserver.pojo.User;
import top.mikevane.ljserver.pojo.UserInfo;

/**
 * @author: whb
 * @date: 2022-11-07-16-53
 * @version: 1.0
 */
public interface UserInfoService {
    /**
     * 添加 userinfo
     * @param user user信息
     * @return 返回插入信息的主键值，反之失败
     */
    int addUserInfo(User user);

    /**
     * 查找 userInfo
     * @param userInfo userInfo信息
     * @return 成功返回userInfo信息，否则为空
     */
    UserInfo[] selectUserInfo(UserInfo userInfo);
}
