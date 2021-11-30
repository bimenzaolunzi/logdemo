package com.itheima.logdemo.user;


import com.itheima.logdemo.utils.LogBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class App {
    private Logger logger = LoggerFactory.getLogger("kafka");

    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class).run(args);
    }

    @RequestMapping("/test")
    public Object test() {
        LogBean logBean = new LogBean(new Random().nextInt(3333) + "", "user", "pc", "java user", "user test request");
        logger.info(logBean.toString());
        return logBean;


    }
}