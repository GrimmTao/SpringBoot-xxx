/*******************************************************************************
 * Copyright (c) 2021, 2021 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.springboot.starter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alex.demo.springboot.starter.pojo.People;

/**
 * @Author alex
 * @Created Feb 2, 2021 11:01:33 AM
 * @Description
 *              <p>
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestConditionOn {

	@Autowired(required = false)
	private People people;

	@Test
	public void test() {
		System.out.println("= = = = = = = = = = = = = ");
		System.out.println("people = " + people);
		System.out.println("= = = = = = = = = = = = = ");
	}
}
