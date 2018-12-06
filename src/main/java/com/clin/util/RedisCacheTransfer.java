package com.clin.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName：RedisCacheTransfer.java
 * @Description：静态注入中间类
 * @author zxt
 * @Date：2018年3月29日 上午10:14:35
 */
@Component
public class RedisCacheTransfer {
	@Autowired
	public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
		RedisCache.setJedisConnectionFactory(jedisConnectionFactory);
	}
}
