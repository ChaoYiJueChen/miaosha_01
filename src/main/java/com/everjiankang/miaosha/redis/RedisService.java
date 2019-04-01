package com.everjiankang.miaosha.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {
	
	@Autowired
	JedisPool jedisPool;

	/**
	 * 获取单个对象
	 * @param prefix
	 * @param key
	 * @param clazz
	 * @return
	 */
	public <T> T get(KeyPrefix prefix,String key, Class<T> clazz) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String realKey = prefix.getPrefix() + key;
			String value = jedis.get(realKey);
			T t = stringToBean(value,clazz);
			return t;
		} finally {
			returnToPool(jedis);
		}
	}
	
	/**
	 * 设置对象
	 * @param prefix
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(KeyPrefix prefix, String key, Object value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String str = beanToString(value);
			if(str == null || str.length() <=0)
				return false;
			int expireSecond = prefix.expireSeconds();
			String realKey = prefix.getPrefix() + key;
			if(expireSecond <= 0) {
				jedis.set(realKey,str);
			} else {
				jedis.setex(realKey, expireSecond, str);
			}
			return true;
		} finally {
			returnToPool(jedis);
		}
	}
	
	
	
	/**
	 * 判断是否存在
	 * @param prefix
	 * @param key
	 * @return
	 */
	public boolean exist(KeyPrefix prefix, String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String realKey = prefix.getPrefix() + key;
			return jedis.exists(realKey);
		} finally {
			returnToPool(jedis);
		}
	}
	
	/**
	 * 增加
	 * @param prefix
	 * @param key
	 * @param clazz
	 * @return
	 */
	public <T> Long incri(KeyPrefix prefix,String key, Class<T> clazz) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String realKey = prefix.getPrefix() + key;
			Long incr = jedis.incr(realKey);
			return incr;
		} finally {
			returnToPool(jedis);
		}
	}
	
	/**
	 * 减少
	 * @param prefix
	 * @param key
	 * @param clazz
	 * @return
	 */
	public <T> Long decr(KeyPrefix prefix,String key, Class<T> clazz) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String realKey = prefix.getPrefix() + key;
			Long decr = jedis.decr(realKey);
			return decr;
		} finally {
			returnToPool(jedis);
		}
	}
	
	/**
	 * Java对象转String
	 * @param value
	 * @return
	 */
	private <T> String beanToString(T value) {
		if(value == null)
			return null;
		Class<?> clazz = value.getClass();
		
		if(clazz == int.class || clazz == Integer.class 
				|| clazz == long.class || clazz == Long.class 
				|| clazz == float.class || clazz == Float.class
				|| clazz == double.class || clazz == Double.class
				)
			return "" + value;
		else if(value instanceof String)
			return (String) value;
		else
			return JSON.toJSONString(value);
		
	}
	
	/**
	 * string 转Java
	 * @param value
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <T> T stringToBean(String value,Class<T> clazz) {
		if(value == null)
			return null;
		if(clazz == int.class || clazz == Integer.class)
			return (T) Integer.valueOf(value);
		else if( clazz == long.class || clazz == Long.class)
			return (T) Long.valueOf(value);
		else if(clazz == float.class || clazz == Float.class)
			return (T) Float.valueOf(value);
		else if(clazz == double.class || clazz == Double.class)
			return (T) Double.valueOf(value);
		else if(value instanceof String) 
			return (T) value;
		else
			return JSON.toJavaObject(JSON.parseObject(value), clazz);
	}

	/**
	 * 将Jedis链接还回连接池：详情close方法
	 * @param jedis
	 */
	private void returnToPool(Jedis jedis) {
		if(jedis != null)
			jedis.close();
	}
}
