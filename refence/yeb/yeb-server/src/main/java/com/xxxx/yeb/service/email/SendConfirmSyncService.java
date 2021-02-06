package com.ybzn.yeb.service.email;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

/**
 * auth:Administrator
 * time:2020/7/25 0025 08:17
 */
@Service
public class SendConfirmSyncService {
    private static final String QUEUE_NAME = "topics";

    public void sendmq (String str) {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.10.100");
        factory.setPort(5672);
        factory.setUsername("root");
        factory.setPassword("root");
        factory.setVirtualHost("/root");
        Connection connection = null;
        Channel channel = null;

        try {
            // 通过工厂创建连接
            connection = factory.newConnection();
            // 获取通道
            channel = connection.createChannel();
            // 开启confirm确认模式
            channel.confirmSelect();

            // 声明队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // 创建消息
            String message = str;
            // 将产生的消息放入队列
            try {
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            System.out.println(" [x] Sent '" + message + "'");
            // 确认消息是否发送成功-单条
            if (channel.waitForConfirms()) {
                System.out.println("消息发送成功！");
            } else {
                System.out.println("消息发送失败！");
            }
            // 确认消息是否发送成功-多条
            // 直到所有消息都确认，只要有一个未确认就会IOException
            // channel.waitForConfirmsOrDie();
            // System.out.println("消息发送成功！");
        } catch (Exception e) {
            try {
                connection.close();
                channel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (TimeoutException e1) {
                e1.printStackTrace();
            }
        }
    }
}
