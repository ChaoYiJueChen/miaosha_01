package com.everjiankang.miaosha.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everjiankang.miaosha.dao.UserMapper;
import com.everjiankang.miaosha.model.User;
import com.everjiankang.miaosha.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper mapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(User record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(User record) {
		return mapper.insertSelective(record);
	}

	@Override
	public User selectByPrimaryKey(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(User record) {
		return mapper.updateByPrimaryKey(record);
	}
    
}
