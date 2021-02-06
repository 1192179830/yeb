package com.ybzn.yeb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 *
 * @author zhoubin
 * @since 1.0.0
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan (basePackages = "com.xxxx.yeb.mapper")
public class YebApplication {

    public static void main (String[] args) {
        SpringApplication.run(YebApplication.class, args);
    }

}
