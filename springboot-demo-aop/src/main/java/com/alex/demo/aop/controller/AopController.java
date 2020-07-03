package com.alex.demo.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alex.demo.aop.annotation.LogAnnotation;
import com.alex.demo.aop.javabean.User;

/**
 * @Author Alex
 * @Created Dec 2020/3/27 17:00
 * @Description
 *              <p>
 */
@RestController
@RequestMapping("/aopcontroller")
public class AopController {

	@GetMapping("/doWithLogAnnotation")
	@LogAnnotation(logDesc = "hello log")
	public String doWithLogAnnotation(String name, String age, User user) throws Exception {
		System.out.println("【执行方法】：doWithLogAnnotation");
		return "doWithLogAnnotation";
	}

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

	@GetMapping("/testParam/{name}/{age}")
	public String testParam(@PathVariable String name, @PathVariable int age) {
		System.out.println("【执行方法】：testParam");
		return "testParam";
	}

}