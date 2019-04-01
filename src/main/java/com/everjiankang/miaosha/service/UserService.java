package com.everjiankang.miaosha.service;

import com.everjiankang.miaosha.model.User;

public interface UserService {
	int deleteByPrimaryKey(Integer id);

    int insert(User record);


    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
