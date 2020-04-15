/*******************************************************************************
 * Copyright (c) 2020, 2020 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.alex.demo.mybatis.javabean.User;
import com.alex.demo.mybatis.util.CommonMapper;

/**
 * @Author Alex
 * @Created Dec 2020/3/27 16:00
 * @Description
 *              <p>
 */
public interface UserMapper extends CommonMapper<User> {

	@Select("select * from user")
	List<User> findAll();

	@Options(useGeneratedKeys = true, keyProperty = "id") // 设置之间id自增
	@Insert("insert into user(name,age,create_date,update_date) values(#{name},#{age},#{createDate},#{updateDate})")
	int userInsert(User user);

	@Delete("delete from user where id=#{id}")
	int deleteById(Integer id);

	@Select("select * from user where id=#{id}")
	User findById(Integer id);

	@Update("update user set name=#{name},age=#{age},update_date=#{updateDate} where id=#{id}")
	void update(User user);

}
