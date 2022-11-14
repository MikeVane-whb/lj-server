package top.mikevane.ljserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mikevane.ljserver.mapper.UserInfoMapper;
import top.mikevane.ljserver.pojo.User;
import top.mikevane.ljserver.pojo.UserInfo;
import top.mikevane.ljserver.service.UserInfoService;

/**
 * @author: whb
 * @date: 2022-11-07-16-54
 * @version: 1.0
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public int addUserInfo(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setPhone(user.getPhone());
        userInfo.setUsername(user.getPhone());
        userInfo.setSex("男");
        // 插入信息
        userInfoMapper.insertUserInfo(userInfo);
        return userInfo.getId();
    }

    @Override
    public UserInfo[] selectUserInfo(UserInfo userInfo) {
        return userInfoMapper.selectUserInfo(userInfo);
    }
}
