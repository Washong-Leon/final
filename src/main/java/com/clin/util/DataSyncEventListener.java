package com.clin.util;

import com.clin.controller.WSServer;
import com.clin.entity.User;
import com.clin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

public class DataSyncEventListener implements MessageListener {
 /*   @Autowired
    private RedisSerializer<String> stringRedisSerializer;

    @Autowired
    private RedisSerializer<Object> jsonRedisSerializer;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = stringRedisSerializer.deserialize(message.getChannel());
        Object object = jsonRedisSerializer.deserialize(message.getBody());
        System.out.println("channel is ：" + channel);
        System.out.println("message is ：" + object.toString());
    }*/
 @Autowired
 private RedisTemplate<String, String> redisTemplate;
    @Resource(name="userService")
    private UserService userService;
    @Override
    public void onMessage(Message message, byte[] pattern) {
        RedisSerializer<?> serializer = redisTemplate.getValueSerializer();
        Object channel = serializer.deserialize(message.getChannel());
        Object body = serializer.deserialize(message.getBody());
        System.out.println("主题: " + channel);//找出订阅此频道的，推送
        System.out.println(channel.toString());
        List<User> m=userService.query_friend(channel.toString());
        WSServer wsServer=new WSServer();
        for(int i=0;i<m.size();i++){
            System.out.println("粉丝是:"+m.get(i).getUid());
            try {
                wsServer.sendAllFans(m.get(i).getUid());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("消息内容: " + String.valueOf(body));
    }

    public RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

}
