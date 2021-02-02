/*******************************************************************************
 * Copyright (c) 2021, 2021 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.springboot.starter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alex.demo.springboot.starter.pojo.City;
import com.alex.demo.springboot.starter.pojo.People;

/**
 * @Author alex
 * @Created Feb 2, 2021 11:00:01 AM
 * @Description
 *              <p>
 */
@Configuration
public class CustomConfiguration {

	@Bean
	public City city() {
		City city = new City();
		city.setCityName("上海");
		return city;
	}

	/**
	 * 如果city存在，才实例化people
	 * 如果把上面的city()那段代码屏蔽了，则此处就不会实例化这个People，测试类TestConditionOn中的people也就为null
	 */
	@Bean
	@ConditionalOnBean(name = "city")
	public People people(City city) {
		// 这里如果city实体没有成功注入 这里就会报空指针
		city.setCityCode(301701);
		return new People("张三", 25, city);
	}
}
