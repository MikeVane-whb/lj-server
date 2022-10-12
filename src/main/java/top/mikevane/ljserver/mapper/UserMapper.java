package top.mikevane.ljserver.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.mikevane.ljserver.pojo.User;

/**
 * @author: whb
 * @date: 2022-09-15-15-36
 * @version: 1.0
 */
@Mapper
public interface UserMapper {
    /**
     * 根据传入User查询用户信息
     * @param user 用户id/email
     * @return
     */
    User[] selectByUser(@Param("user") User user);
}
