/*******************************************************************************
 * Copyright (c) 2020, 2020 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.mybatis.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alex.demo.mybatis.dao.UserMapper;
import com.alex.demo.mybatis.javabean.User;

/**
 * @Version 1.0
 * @Author zepei.tao@hirain.com
 * @Created Dec 2020/3/27 16:05
 * @Description
 *              <p>
 * @Modification
 *               <p>
 *               Date Author Version Description
 *               <p>
 *               2020/3/27 zepei.tao@hirain.com 1.0 create file
 */
@RestController
@RequestMapping("/mybatistest")
public class TestMybatisController {

	@Autowired
	private UserMapper userMapper;

	@GetMapping("/getAll")
	public List<User> getAllUser() {
		List<User> all = userMapper.findAll();
		System.out.println(all.size());
		return all;
	}

	@PostMapping("/insert")
	public void userInsert(@RequestBody User user) {
		LocalDateTime now = LocalDateTime.now();
		user.setCreateDate(now);
		user.setUpdateDate(now);
		userMapper.userInsert(user);
	}

	// TODO 这个接口有问题，待测
	@GetMapping(value = "/find")
	@ResponseBody
	public User findUser(Integer id) {
		User user = userMapper.findById(id);
		System.out.println(user.toString());
		return user;
	}

	@DeleteMapping(value = "/delete")
	public void delete(Integer id) {
		userMapper.deleteById(id);
	}

	@PutMapping(value = "/update")
	public void update(@RequestBody User user) {
		user.setUpdateDate(LocalDateTime.now());
		userMapper.update(user);
	}
}
