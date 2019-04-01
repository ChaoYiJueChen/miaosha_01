package com.everjiankang.miaosha.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CodeMsg {
	
	private int code;
	
	private String msg;
	
	//通用异常
	public static CodeMsg SUCCESS = new CodeMsg(0, "success");
	public static CodeMsg SERVER_ERROR = new CodeMsg(500100,"服务端异常");
	
	//登录异常5002XXX
	public static CodeMsg LOGIN_ERROR = new CodeMsg(500200, "登录异常");

	//商品异常5003XXX
	
	//订单异常5004XXX
	
	//秒杀异常5005XXX
}
