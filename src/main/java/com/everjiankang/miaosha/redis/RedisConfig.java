package com.everjiankang.miaosha.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("redis")
public class RedisConfig {
	private String host;
	private String username;
	private String passport;
	private int port;
	private int timeout;
	private String   passpord;
	private int  poolMaxTotal;
	private int  poolMaxIdle;
	private int  poolMaxWait;
}