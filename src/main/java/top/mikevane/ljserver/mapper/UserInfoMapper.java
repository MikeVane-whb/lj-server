package top.mikevane.ljserver.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.mikevane.ljserver.pojo.UserInfo;

/**
 * @author: whb
 * @date: 2022-11-07-16-54
 * @version: 1.0
 */
@Mapper
public interface UserInfoMapper {
    /**
     * 插入一条 userInfo
     * @param userInfo userInfo
     * @return 成功返回更新条数，失败返回 0
     */
    int insertUserInfo(UserInfo userInfo);

    /**
     * 查询 userInfo 信息
     * @param userInfo userInfo
     */
    UserInfo[] selectUserInfo(@Param("userInfo") UserInfo userInfo);

    /**
     * 更新 userinfo 信息
     * @param userInfo
     * @return 成功返回主键 id
     */
    int update(@Param("userInfo") UserInfo userInfo);
}
