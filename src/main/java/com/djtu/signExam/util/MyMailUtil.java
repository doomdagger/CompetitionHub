package com.djtu.signExam.util;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by JOECHOW on 2014/8/29.
 */
public class MyMailUtil {

    /**
     *
     * @param title:邮件标题
     * @param content：邮件内容
     * @param to：收件人，多个收件人中间用逗号隔开
     * @return
     */
    public static boolean sendMail(String title,String content,String to) {
        try {
            InputStream in = MyMailUtil.class.getResourceAsStream("/com/djtu/signExam/config/email.properties");
            Properties prop = new Properties();
            prop.load(in);
            //创建邮件session
            Session session = Session.getInstance(prop);
            // 创建邮件对象
            Message msg = new MimeMessage(session);
            // 发件人
            msg.setFrom(new InternetAddress(prop.getProperty("mail.user.account")));
            // 多个收件人 InternetAddress.parse("java_mail_002@163.com,java_mail_003@163.com"))逗号隔开
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            // 主题
            msg.setSubject(title);
            // HTML内容
            msg.setContent(content, "text/html;charset=utf-8");
            // 连接邮件服务器、发送邮件、关闭连接，全干了
            Transport transport = session.getTransport();
            transport.connect(prop.getProperty("mail.host"), prop.getProperty("mail.user.account"), prop.getProperty("mail.user.password"));
            //Transport.send(msg);
            transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
