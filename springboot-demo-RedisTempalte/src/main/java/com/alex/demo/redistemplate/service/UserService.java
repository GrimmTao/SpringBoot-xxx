/*******************************************************************************
 * Copyright (c) 2020, 2020 Alex.
 ******************************************************************************/
package com.alex.demo.redistemplate.service;

import java.util.List;

import com.alex.demo.redistemplate.javabean.User;

/**
 * @Author Alex
 * @Created Dec 2020/3/23 15:32
 * @Description
 *              <p>
 */
public interface UserService {

	void add(User user);

	void delete(Integer id);

	void update(User user);

	List<User> getAll();

	User findUser(Integer id);
}
