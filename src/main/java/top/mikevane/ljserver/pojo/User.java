package top.mikevane.ljserver.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: whb
 * @date: 2022-09-15-15-34
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户详细信息id
     */
    private String userInfoId;
    /**
     * 用户 token（注意定期清理）
     */
    private String token;
}
