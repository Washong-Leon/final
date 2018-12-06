package com.clin.util;

import com.clin.controller.WSServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.applet.AppletContext;

public class test {
    public static void main(String args[]) {
   /*     ApplicationContext ac=new ClassPathXmlApplicationContext("spring-redis.xml");
        RedisService redisService=(RedisService)ac.getBean("redisService");
        redisService.sendMessage("edc fnc","qwe");
*/
        WSServer wsServer=new WSServer();

    }
}
