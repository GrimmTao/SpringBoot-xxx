/*******************************************************************************
 * Copyright (c) 2021, 2021 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.springboot.starter;

import java.util.concurrent.ThreadPoolExecutor;

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

	@Autowired
	private ThreadPoolExecutor videoThreadPool;

	@Autowired
	private ThreadPoolExecutor multicastReceiveThreadPool;

	@Autowired
	private ThreadPoolExecutor messageStorageThreadPool;

	@Test
	public void testCustomConfiguration() {
		System.out.println("= = = = = = = = = = = = = ");
		System.out.println("people = " + people);
		System.out.println("= = = = = = = = = = = = = ");
	}

	@Test
	public void testThreadPool() {
		System.out.println("size1:" + videoThreadPool.getCorePoolSize());
		System.out.println("size2:" + multicastReceiveThreadPool.getCorePoolSize());
		System.out.println("size3:" + messageStorageThreadPool.getCorePoolSize());
	}
}
