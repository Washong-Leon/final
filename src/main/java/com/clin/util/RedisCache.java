package com.clin.util;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.exceptions.JedisConnectionException;
// redis模板类，提供了对缓存的增删改查 
public class RedisCache implements Cache {
	/** * 静态成员变量无法直接使用spring注解注入 */
	private static JedisConnectionFactory jedisConnectionFactory;
	private final String id;
	/** * The {@code ReadWriteLock}. */
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	public RedisCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		this.id = id;
	}

	public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
		RedisCache.jedisConnectionFactory = jedisConnectionFactory;
	}

	@Override
	public void clear() {
		RedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			connection.flushDb();
			connection.flushAll();
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public Object getObject(Object key) {
		Object result = null;
		RedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
			result = serializer.deserialize(connection.get(serializer.serialize(key)));
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return result;
	}

	@Override
	public void putObject(Object key, Object value) {
		RedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
			connection.set(serializer.serialize(key), serializer.serialize(value));
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	@Override
	public Object removeObject(Object key) {
		RedisConnection connection = null;
		Object result = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();
			result = connection.expire(serializer.serialize(key), 0);
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return result;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		// TODO Auto-generated method stub
		return this.readWriteLock;
	}

	@Override
	public int getSize() {
		int result = 0;
		RedisConnection connection = null;
		try {
			connection = jedisConnectionFactory.getConnection();
			result = Integer.valueOf(connection.dbSize().toString());
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return result;
	}
	

}