package com.everjiankang.miaosha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.everjiankang.miaosha.model.CodeMsg;
import com.everjiankang.miaosha.model.Result;
import com.everjiankang.miaosha.model.User;
import com.everjiankang.miaosha.redis.RedisService;
import com.everjiankang.miaosha.redis.UserKey;
import com.everjiankang.miaosha.service.UserService;


@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	RedisService redisService;

	@PostMapping("/test01")
	@ResponseBody
	public Result<String> test01() {
		return Result.success("hello world");
	}
	
	@PostMapping("/helloError")
	@ResponseBody
	public Result<String> helloError() {
		return Result.error(CodeMsg.SERVER_ERROR);
	}
	
	@RequestMapping("/thymeleaf")
	public String thymeleaf() {
		return "thymeleaf";
	}
	
	@RequestMapping("/getById/{id}")
	@ResponseBody
	public Result<User> getById(@PathVariable("id") int id) {
		return Result.success(userService.selectByPrimaryKey(id));
	}
	
	@RequestMapping("/redisGet/{key}")
	@ResponseBody
	public Result<String> redisGet(@PathVariable("key") String key) {
		String string = redisService.get(UserKey.getById,key, String.class);
		return Result.success(string);
	}
	
	@RequestMapping("/redisSet/{key}/{value}")
	@ResponseBody
	public Result<String> redisSet(@PathVariable("key") String key,@PathVariable("value") String value) {
		if(key != null && !"".equals(key.trim()) && value != null && !"".equals(value)) {
			boolean result = redisService.set(UserKey.getById,key, value);
			if(result)
				return Result.success(redisService.get(UserKey.getById,key, String.class));
			else
				return Result.error(CodeMsg.SERVER_ERROR);
		} else {
			return Result.error(CodeMsg.SERVER_ERROR);
		}
	}
	
	@RequestMapping("/redisSetUserTest")
	@ResponseBody
	public Result<User> redisSetUserTest(@PathVariable("key") String key) {
		User user = new User();
		user.setId(1);
		user.setAge(27);
		user.setName("xiaochao");
		user.setSex(1);
		boolean result = redisService.set(UserKey.getById,key, user);
		if(result)
			return Result.success(user);
		else
			return Result.error(CodeMsg.SERVER_ERROR);
	}
	
	@RequestMapping("/redisSetUserTest")
	@ResponseBody
	public Result<User> redisGetUserTest(@PathVariable("id") String id) {
		
		User user = redisService.get(UserKey.getById,id,User.class);
		if(user != null)
			return Result.success(user);
		else
			return Result.error(CodeMsg.SERVER_ERROR);
	}
	
}