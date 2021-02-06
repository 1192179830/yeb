package com.ybzn.yeb.controller.email;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * auth:Administrator
 * time:2020/7/23 0023 19:56
 */
@Controller
@RequestMapping ("/email")
public class EmailController {
    @RequestMapping ("/send")
    public void sendMessage () throws MessagingException {
        Properties properties = new Properties();
        properties.setProperty("mail.host", "smtp.qq.com");
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.port", "465");
        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);
        Address address = new InternetAddress();
        ((InternetAddress) address).setAddress("826081491@qq.com");
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.addRecipients(Message.RecipientType.TO, "3073629434@qq.com");//设置收信人
        mimeMessage.addRecipients(Message.RecipientType.CC, "615947923@qq.com");//抄送
        mimeMessage.setFrom(address);//邮件发送人
        mimeMessage.setSubject("测试邮件主题");//邮件主题
        mimeMessage.setContent("Hello,这是一封测试邮件", "text/html;charset=utf-8");//正文

        Transport transport = session.getTransport();
        transport.connect("smtp.qq.com", "826081491@qq.com", "vwzunypczlztbdjh");
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());//发送邮件，第二个参数为收件人
        transport.close();
    }
}
