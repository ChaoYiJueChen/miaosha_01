package com.everjiankang.miaosha.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
	@Getter
	private int code;
	@Getter
	private String msg;
	@Getter
	private T data;
	
	private Result(CodeMsg codeMsg) {
		this.code = codeMsg.getCode();
		this.msg = codeMsg.getMsg();
	}
	
	/** 成功只传数据 */
	public static<T> Result<T> success(T data) {
		return new Result<>(0,"success",data);
	}
	
	/** 失败只传code和msg */
	public static<T> Result<T> error(CodeMsg codeMsg) {
		return new Result<>(codeMsg);
	}
}