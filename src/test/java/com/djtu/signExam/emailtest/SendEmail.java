package com.djtu.signExam.emailtest;

import com.djtu.signExam.util.MailUtil;
import com.djtu.signExam.util.MyMailUtil;
import com.djtu.signExam.util.SessionUtil;
import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.Properties;

/**
 * Created by JOECHOW on 2014/8/29.
 */
public class SendEmail {

    public void SendEmail() throws FileNotFoundException {
        //send email
        try {
            InputStream in = SendEmail.class.getResourceAsStream("/com/djtu/signExam/config/email.properties");
            Properties prop = new Properties();
            prop.load(in);
            Session session = Session.getInstance(prop);
            // 创建邮件对象
            Message msg = new MimeMessage(session);
            // 发件人
            msg.setFrom(new InternetAddress(prop.getProperty("mail.user.account")));
            // 多个收件人
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("787449527@qq.com"));
            // 主题
            msg.setSubject("中文主题");
            // HTML内容
            msg.setContent("<div align=\"center\">你好啊</div>", "text/html;charset=utf-8");

            // 连接邮件服务器、发送邮件、关闭连接，全干了
            Transport transport = session.getTransport();
            transport.connect(prop.getProperty("mail.host"),prop.getProperty("mail.user.account"),prop.getProperty("mail.user.password"));
            //Transport.send(msg);
            transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.print("Error");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUtil(){
        MyMailUtil.simpleSendMail("我是小辉","没有内容","787449527@qq.com");
    }
}
