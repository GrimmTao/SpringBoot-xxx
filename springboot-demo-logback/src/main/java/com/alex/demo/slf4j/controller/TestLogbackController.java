/*******************************************************************************
 * Copyright (c) 2020, 2020 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.slf4j.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alex.demo.slf4j.javabean.User;

/**
 * @Version 1.0
 * @Author zepei.tao@hirain.com
 * @Created Dec 2020/3/29 19:50
 * @Description
 *              <p>
 * @Modification
 *               <p>
 *               Date Author Version Description
 *               <p>
 *               2020/3/29 zepei.tao@hirain.com 1.0 create file
 */
@RestController
@RequestMapping("/slf4j")
public class TestLogbackController {

	private static final Logger logger = LoggerFactory.getLogger(TestLogbackController.class);

	@PostMapping("/logback")
	public void testSlf4j(User user) {
		if (user.getId() != null) {
			logger.info(user.toString());
		} else {
			logger.error("user id 不能为 null");
		}
	}
}
