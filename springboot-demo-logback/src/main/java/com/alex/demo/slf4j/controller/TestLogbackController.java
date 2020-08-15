/*******************************************************************************
 * Copyright (c) 2020, 2020 Alex.
 ******************************************************************************/
package com.alex.demo.slf4j.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alex.demo.slf4j.javabean.User;

/**
 * @Author Alex
 * @Created Dec 2020/3/29 19:50
 * @Description
 *              <p>
 */
@RestController
@RequestMapping("/slf4j")
public class TestLogbackController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping("/logback")
	public void testSlf4j(User user) {
		if (user.getId() != null) {
			logger.info(user.toString());
		} else {
			logger.error("user id 不能为 null");
		}
	}
}
