package top.mikevane.ljserver.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Random;

/**
 * @author: whb
 * @date: 2022-11-04-16-40
 * @version: 1.0
 */
@Slf4j
@Component
public class EmailUtil {

    private static JavaMailSenderImpl javaMailSender;

    @Autowired
    private void setJavaMailSender(JavaMailSenderImpl mailSender){
        EmailUtil.javaMailSender = mailSender;
    }

    public static void sendCode(String phone, HttpSession session){
        log.info("发送邮件");
        String sessionId = session.getId();
        log.info("sessionId: " + sessionId);
        String email = phone + "@139.com";
        // 获取随机验证码
        String randomForCodePhone = getRandomForCodePhone();
        //创建简单的邮件发送对象
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1312706383@qq.com");           // 设置发件人邮箱（若配置默认邮箱则不用再设置）
        message.setTo(email);            // 设置收件人邮箱
        message.setSubject("lj 验证码");                  // 设置邮件主题
        message.setText("您的验证码为:"+ randomForCodePhone +",欢迎访问 lj"); // 设置邮件文本内容
        message.setSentDate(new Date());                // 设置邮件发送时间
        //发送
        // javaMailSender.send(message);
        session.setAttribute("sessionId",sessionId);
        session.setAttribute("phone",phone);
        session.setAttribute("verityCode",randomForCodePhone);
        log.info("邮件发送成功");
    }

    /**
     * 生成随机验证码
     */
    private static String getRandomForCodePhone() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10));
        }
        log.info(sb.toString());
        return sb.toString();
    }
}
