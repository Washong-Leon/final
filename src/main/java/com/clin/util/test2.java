package com.clin.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test2 {
    public static void main(String args[]) {
        ApplicationContext ac=new ClassPathXmlApplicationContext("spring-redis.xml");
        RedisService redisService=(RedisService)ac.getBean("redisService");
        redisService.sendMessage("edc","ccccc");
    }
}
