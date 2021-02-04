package com.ybzn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Hugo
 * @time 2021/1/20
 */
@SpringBootApplication
@MapperScan("com.ybzn.mapper")
public class YebApplication{
    public static void main (String[] args) {
        SpringApplication.run(YebApplication.class,args);
    }
}
