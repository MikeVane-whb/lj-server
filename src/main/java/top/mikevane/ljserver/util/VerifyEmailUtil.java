package top.mikevane.ljserver.util;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.ArrayList;
import java.util.Properties;

/**
 * @author: whb
 * @date: 2022-10-07-21-41
 * @version: 1.0
 */
public class VerifyEmailUtil {
    public static JavaMailSenderImpl javaMailSender;
    public static Properties prop;

    public VerifyEmailUtil(){
        prop = new Properties();
        prop.setProperty("mail.host", "smtp.139.com");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.smtp.port", "25");
        javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.qq.com");
        javaMailSender.setPort(587);
        javaMailSender.setUsername("1312706383@qq.com");
        javaMailSender.setPassword("lwrupkmlhcazhfjb");
        javaMailSender.setJavaMailProperties(prop);
        ArrayList
    }


}
