package com.alex.demo.cookie.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
@CrossOrigin
public class HelloSessionController {

	@RequestMapping("/add")
	public String addSession(HttpServletRequest httpServletRequest, @RequestParam("username") String username) {
		HttpSession session = httpServletRequest.getSession();
		session.setAttribute("username", username);
		session.setMaxInactiveInterval(10000);
		return "添加成功";
	}

	@RequestMapping("/show")
	public Object showSession(HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		Object object = session.getAttribute("username");
		return object;
	}
}