package com.ybzn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Hugo
 * @time 2021/1/19
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableFeignClients
@MapperScan ("com.ybzn.entity.*")
@ComponentScan (basePackages = {"com.gitee.sunchenbin.mybatis.actable.manager.*"})
public class DemoMybatisAcTable {
    public static void main (String[] args) {
        SpringApplication.run(DemoMybatisAcTable.class, args);
    }
}
