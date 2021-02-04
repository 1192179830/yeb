package com.xxxx.yeb.controller;

import com.xxxx.yeb.pojo.Admin;
import com.xxxx.yeb.query.AdminQuery;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * auth:Administrator
 * time:2020/7/22 0022 11:12
 */
@RestController
@RequestMapping ("/chat")
public class ChatController {
    @RequestMapping ("/admin")
    public AdminQuery initData (HttpServletRequest request) {
        System.out.println((String) request.getSession().getAttribute("username"));
        AdminQuery adminQuery = new AdminQuery();
        adminQuery.setUsername((String) request.getSession().getAttribute("username"));
        return adminQuery;
    }

}
