/*******************************************************************************
 * Copyright (c) 2020, 2020 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.jpa.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alex.demo.jpa.dao.UserRepository;
import com.alex.demo.jpa.javabean.User;

/**
 * @Author Alex
 * @Created Dec 2020/3/27 17:00
 * @Description
 *              <p>
 */
@RestController
@RequestMapping("/jpa")
public class TestJPAController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/getAll")
	public List<User> findAll() {
		List<User> all = userRepository.findAll();
		System.out.println(all.size());
		return all;
	}

	@PostMapping("/insert")
	public void insert(@RequestBody User user) {
		LocalDateTime now = LocalDateTime.now();
		user.setCreateDate(now);
		user.setUpdateDate(now);
		userRepository.save(user);
	}

	@DeleteMapping(value = "/delete")
	public void delete(Integer id) {
		userRepository.deleteById(id);
	}

	@GetMapping(value = "/find")
	@ResponseBody
	public User findUser(Integer id) {
		Optional<User> user = userRepository.findById(id);
		System.out.println(user.get().toString());
		return user.get();
	}

	@PutMapping(value = "/update")
	public void update(@RequestBody User user) {
		user.setUpdateDate(LocalDateTime.now());
		// TODO 需要在UserRepository中自己实现update方法，具体百度
	}

}
