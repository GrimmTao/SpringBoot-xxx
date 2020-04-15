package com.hirain.redis.demo.redistemplate.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.hirain.redis.demo.redistemplate.javabean.User;
import com.hirain.redis.demo.redistemplate.service.UserService;
import com.hirain.redis.demo.redistemplate.util.RedisConstants;
import com.hirain.redis.demo.redistemplate.util.RedisUtil;
import com.hirain.redis.demo.redistemplate.util.StateParameter;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author Alex
 * @Created Dec 2020/3/20 13:32
 * @Description
 *              <p>
 */
@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisTestController extends BaseController {

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private UserService userService;

	@PostMapping("/add")
	public void addUser(@RequestBody User user) {
		LocalDateTime now = LocalDateTime.now();
		user.setCreateDate(now);
		user.setUpdateDate(now);
		userService.add(user);
	}

	@GetMapping("/findAll")
	public List<User> findAll() {
		List<User> all = userService.getAll();
		return all;
	}

	@GetMapping("/find")
	public User findById(Integer id) {
		User user = userService.findUser(id);
		System.out.println(user.toString());
		return user;
	}

	@DeleteMapping("/delete")
	public void deleteById(Integer id) {
		userService.delete(id);
	}

	@PutMapping("/update")
	public void update(@RequestBody User user) {
		user.setUpdateDate(LocalDateTime.now());
		userService.update(user);
	}

	/**
	 * 测试Redis连接池切换（未成功）
	 */
	@GetMapping(value = "/testPoolChoose")
	public void testPoolChoose() {
		for (int i = 0; i < 3; i++) {
			redisUtil.get("test", i);
		}
	}

	/**
	 * @Description: 测试redis存储&读取
	 * @return: org.springframework.ui.ModelMap
	 */
	@GetMapping(value = "/test")
	@ResponseBody
	public ModelMap test() {
		try {
			redisUtil.set("redisTemplate", "这是一条测试数据", RedisConstants.datebase2);
			String value = redisUtil.get("redisTemplate", RedisConstants.datebase2).toString();
			log.info("redisValue=" + value);
			log.info("读取redis成功");
			return getModelMap(StateParameter.SUCCESS, value, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return getModelMap(StateParameter.FAULT, null, "操作失败");
		}
	}

	@RequestMapping(value = "/setUser")
	@ResponseBody
	public ModelMap setUser() {
		try {
			User user = new User();
			user.setName("詹姆斯");
			user.setAge(35);
			redisUtil.set("user", JSONObject.toJSONString(user), RedisConstants.datebase1);
			User res = (User) redisUtil.get("user", RedisConstants.datebase1);
			log.info("res=" + res.toString());
			log.info("读取redis成功");
			return getModelMap(StateParameter.SUCCESS, res, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return getModelMap(StateParameter.FAULT, null, "操作失败");
		}
	}

}