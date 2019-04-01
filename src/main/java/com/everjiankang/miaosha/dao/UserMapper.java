package com.everjiankang.miaosha.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.everjiankang.miaosha.model.User;

@Mapper
public interface UserMapper {

    @Delete("delete from user where id = #{id}")
    int deleteByPrimaryKey(Integer id);

    @Insert("insert into user(name,age,sex) values(#{name},#{age},#{sex})")
    int insert(User record);


    int insertSelective(User record);

    @Select("select * from user where id = #{id}")
    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    @Update("update user set name=#{name}, age=#{age}, sex=#{sex}")
    int updateByPrimaryKey(User record);
}