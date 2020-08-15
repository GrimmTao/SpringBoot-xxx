/*******************************************************************************
 * Copyright (c) 2020, 2020 Alex.
 ******************************************************************************/
package com.alex.demo.jpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alex.demo.jpa.javabean.User;

/**
 * @Author Alex
 * @Created Dec 2020/3/27 17:17
 * @Description
 *              <p>
 */
// 继承JpaRepository来完成对数据库的操作
public interface UserRepository extends JpaRepository<User, Integer> {

}
