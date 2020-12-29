/*******************************************************************************
 * Copyright (c) 2020, 2020 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.alex.demo.mybatis.javabean.Person;
import com.alex.demo.mybatis.javabean.User;
import com.alex.demo.mybatis.util.CommonMapper;

/**
 * @Version 1.0
 * @Author Alex
 * @Description
 *              <p>
 */
public interface PersonMapper extends CommonMapper<User> {

	@Select("select * from person")
	List<Person> listPersons();

	@Options(useGeneratedKeys = true, keyProperty = "id") // 设置之间id自增
	@Insert("insert into person(age,role,create_date,update_date) values(#{age},#{role},#{createDate},#{updateDate})")
	int insertPerson(Person person);
}
