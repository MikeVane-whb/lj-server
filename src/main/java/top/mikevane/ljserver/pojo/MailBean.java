package top.mikevane.ljserver.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 发送邮箱-封装接受者
 * @author: whb
 * @date: 2022-11-04-16-45
 * @version: 1.0
 */
@Data
public class MailBean implements Serializable {
    private String recipient;   //邮件接收人
    private String subject; //邮件主题
    private String content; //邮件内容
}
