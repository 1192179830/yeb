package com.xxxx.yeb.controller.email;

import java.io.UnsupportedEncodingException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * auth:Administrator
 * time:2020/7/24 0024 16:36
 */
@Controller
@RequestMapping ("/topic")
public class SendConfirmSync {


}
