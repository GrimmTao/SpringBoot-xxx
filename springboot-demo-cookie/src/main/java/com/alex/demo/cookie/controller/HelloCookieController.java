package com.alex.demo.cookie.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cookie")
public class HelloCookieController {

	@PostMapping("/add")
	public String addCookie(HttpServletRequest request, HttpServletResponse response, @RequestParam("username") String username) {
		// 请求行中的资源名部分
		System.out.println("requestURI:" + request.getRequestURI());
		// 客户端发出请求时的完整URL
		System.out.println("requestURL:" + request.getRequestURL().toString());
		// 客户机请求方式
		System.out.println("method:" + request.getMethod());
		// 请求行中的参数部分
		System.out.println("queryString:" + request.getQueryString());
		// 客户机的IP地址
		System.out.println("remoteAddr:" + request.getRemoteAddr());
		// 客户机的完整主机名
		System.out.println("remoteHost:" + request.getRemoteHost());
		// 客户机所使用的网络端口号
		System.out.println("remotePort:" + request.getRemotePort());
		// sessinId
		System.out.println("sessionId:" + request.getRequestedSessionId());

		// 创建⼀个 cookie
		Cookie cookie = new Cookie("username", username);
		cookie.setPath(request.getContextPath());
		// 设置 cookie过期时间
		cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
		// 添加到 response 中
		response.addCookie(cookie);
		return "添加成功";
	}

	@PostMapping("/show")
	public String showCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username")) {
				System.out.println(cookie.getName());
				System.out.println(cookie.getValue());
				return cookie.getValue().toString();
			}
		}
		return "null";
	}

}