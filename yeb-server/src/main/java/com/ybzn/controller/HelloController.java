package com.ybzn.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hugo
 * @time 2021/1/23
 */
@RestController
@Api (tags = "测试可用性 Controller")
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hallo";
    }
}
