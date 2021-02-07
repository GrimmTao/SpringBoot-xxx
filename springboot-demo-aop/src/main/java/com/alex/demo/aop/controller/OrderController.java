/*******************************************************************************
 * Copyright (c) 2021, 2021 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alex.demo.aop.javabean.User;

/**
 * @Author alex
 * @Created Dec 2021/2/7 9:57
 * @Description
 *              <p>
 */
@RestController
@RequestMapping("/ordertest")
public class OrderController {

	// @GetMapping("/doWithAopAnnotation")
	// @AopAnnotation(aopDesc = "test order")
	// public String doWithAopAnnotation(String name, String age, User user) throws Exception {
	// System.out.println("【执行方法】：doWithAopAnnotation");
	// return "doWithAopAnnotation";
	// }

	@GetMapping("/doNormal")
	public String doNormal(String name, String age, User user) {
		System.out.println("【执行方法】：doNormal");
		return "doNormal";
	}

	@GetMapping("/doWithException")
	public String doWithException(String name, String age, User user) throws Exception {
		System.out.println("【执行方法】：doWithException");
		if (Integer.valueOf(age) == 0) {
			throw new Exception("年龄不能为0");
		}
		return "doWithException";
	}
}
