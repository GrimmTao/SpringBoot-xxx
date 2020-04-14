/*******************************************************************************
 * Copyright (c) 2020, 2020 Hirain Technologies Corporation.
 ******************************************************************************/
package com.hirain.redis.demo.redistemplate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hirain.redis.demo.redistemplate.dao.UserMapper;
import com.hirain.redis.demo.redistemplate.javabean.User;

import lombok.extern.slf4j.Slf4j;

/**
 * @Version 1.0
 * @Author zepei.tao@hirain.com
 * @Created Dec 2020/3/23 15:34
 * @Description
 *              <p>
 * @Modification
 *               <p>
 *               Date Author Version Description
 *               <p>
 *               2020/3/23 zepei.tao@hirain.com 1.0 create file
 */
@Service
@CacheConfig(cacheNames = "usercache")
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public void add(User user) {
		userMapper.add(user);
	}

	@Override
	@CacheEvict(key = "'userid-'+ #p0")
	public void delete(Integer id) {
		userMapper.deleteById(id);
	}

	/**
	 * 该方法没有返回值，所以在redis存储key-value时，会报错：Cache '***' does not allow 'null' values
	 * 如果把该方法的返回值改为User，key为在@Cacheable中设置的内容，value就是该方法的返回值。
	 * 为了避免这个问题，可在@Cacheable加上：（,unless="#result == null"）
	 */
	@Override
	@Cacheable(key = "'update-'+#p0.id", unless = "#result == null")
	public void update(User user) {
		userMapper.update(user);
	}

	@Override
	@Cacheable(key = "'findAll'")
	public List<User> getAll() {
		return userMapper.getAll();
	}

	@Override
	@Cacheable(key = "'userid-'+ #p0")
	public User findUser(Integer id) {
		User user = userMapper.findUserById(id);
		log.info("执行了查询数据库的操作");
		return user;
	}
}
