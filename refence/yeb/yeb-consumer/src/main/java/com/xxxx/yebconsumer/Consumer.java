package com.ybzn.yebconsumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.io.UnsupportedEncodingException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

@Component

public class Consumer {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @RabbitListener (queues = "topics")
    public void consume (byte[] msg) throws UnsupportedEncodingException, MessagingException {
        String str = new String(msg, "UTF-8");


        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");//开启认证
        properties.setProperty("mail.debug", "true");//启用调试
        properties.setProperty("mail.smtp.timeout", "1000");//设置链接超时
        properties.setProperty("mail.smtp.port", "465");//设置端口
        properties.setProperty("mail.smtp.socketFactory.port", "465");//设置ssl端口
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.addRecipients(Message.RecipientType.TO, str);//设置收信人
        mimeMessage.addRecipients(Message.RecipientType.CC, "615947923@qq.com");//抄送
        mimeMessage.setFrom("826081491@qq.com");//邮件发送人
        mimeMessage.setSubject("测试邮件主题");//邮件主题
        mimeMessage.setContent("Hello,这是一封测试邮件1", "text/html;charset=utf-8");//正文

        Transport transport = session.getTransport();
        transport.connect("smtp.qq.com", "826081491@qq.com", "vwzunypczlztbdjh");
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());//发送邮件，第二个参数为收件人
        transport.close();


        System.out.println("给" + str + "发送邮件成功");
//        String receivedExchange = msg.getMessageProperties().getReceivedExchange();
//        String receivedRoutingKey = msg.getMessageProperties().getReceivedRoutingKey();
//        String replyTo = msg.getMessageProperties().getReplyTo();
//        rabbitTemplate.convertAndSend("replyExchange", replyTo, "消费端的回复");
    }
}
