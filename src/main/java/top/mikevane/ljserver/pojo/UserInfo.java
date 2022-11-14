package top.mikevane.ljserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: whb
 * @date: 2022-11-07-16-51
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    /**
     * id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * email
     */
    private String email;
    /**
     * phone
     */
    private String phone;
    /**
     * 图片地址
     */
    private String userPhoto;
    /**
     * 用户性别
     */
    private String sex;
}
