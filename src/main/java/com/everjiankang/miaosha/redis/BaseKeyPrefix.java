package com.everjiankang.miaosha.redis;

public abstract class BaseKeyPrefix implements KeyPrefix{
	
	private int expireSeconds;
	
	private String prefix;

	public BaseKeyPrefix(int expireSeconds, String prefix) {
		super();
		this.expireSeconds = expireSeconds;
		this.prefix = prefix;
	}

	public BaseKeyPrefix(String prefix) {
		this.expireSeconds = 0;
		this.prefix = prefix;
	}

	@Override
	public int expireSeconds() {	//默认0代表永不过期
		return expireSeconds;
	}

	@Override
	public String getPrefix() {
		String className = getClass().getSimpleName();
		return className + ":" + prefix;
	}
}