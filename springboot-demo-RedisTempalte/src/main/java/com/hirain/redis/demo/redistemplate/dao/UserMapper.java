/*******************************************************************************
 * Copyright (c) 2020, 2020 Hirain Technologies Corporation.
 ******************************************************************************/
package com.hirain.redis.demo.redistemplate.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.hirain.redis.demo.redistemplate.javabean.User;
import com.hirain.redis.demo.redistemplate.util.CommonMapper;

/**
 * @Version 1.0
 * @Author zepei.tao@hirain.com
 * @Created Dec 2020/3/23 15:35
 * @Description
 *              <p>
 * @Modification
 *               <p>
 *               Date Author Version Description
 *               <p>
 *               2020/3/23 zepei.tao@hirain.com 1.0 create file
 */
public interface UserMapper extends CommonMapper<User> {

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into user(name, age,create_date,update_date) values(#{name}, #{age}, #{createDate}, #{updateDate})")
	int add(User user);

	@Delete("delete from user where id=#{id}")
	int deleteById(Integer id);

	@Update("update user set name=#{name},age=#{age},update_date=#{updateDate} where id=#{id}")
	int update(User user);

	@Select("select * from user")
	List<User> getAll();

	@Select("select * from user where id=#{id}")
	User findUserById(Integer id);

}
