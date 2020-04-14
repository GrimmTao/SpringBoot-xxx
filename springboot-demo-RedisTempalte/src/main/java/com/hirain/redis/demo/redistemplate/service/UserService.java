/*******************************************************************************
 * Copyright (c) 2020, 2020 Hirain Technologies Corporation.
 ******************************************************************************/
package com.hirain.redis.demo.redistemplate.service;

import java.util.List;

import com.hirain.redis.demo.redistemplate.javabean.User;

/**
 * @Version 1.0
 * @Author zepei.tao@hirain.com
 * @Created Dec 2020/3/23 15:32
 * @Description
 *              <p>
 * @Modification
 *               <p>
 *               Date Author Version Description
 *               <p>
 *               2020/3/23 zepei.tao@hirain.com 1.0 create file
 */
public interface UserService {

	void add(User user);

	void delete(Integer id);

	void update(User user);

	List<User> getAll();

	User findUser(Integer id);
}
