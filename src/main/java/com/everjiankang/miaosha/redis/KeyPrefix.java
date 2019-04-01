package com.everjiankang.miaosha.redis;

/**
 * 前缀接口
 * @author guchunchao
 *
 */
public interface KeyPrefix {
	/**
	 * 获取过期时间
	 * @return
	 */
	int expireSeconds();
	
	/**
	 * 获取前缀
	 * @return
	 */
	String getPrefix();
}
