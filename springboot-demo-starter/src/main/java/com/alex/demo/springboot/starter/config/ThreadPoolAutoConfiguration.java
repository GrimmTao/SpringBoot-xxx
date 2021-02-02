/*******************************************************************************
 * Copyright (c) 2021, 2021 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.springboot.starter.config;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alex.demo.springboot.starter.factory.MessageStorageThreadFactory;
import com.alex.demo.springboot.starter.factory.MulticastReceiveThreadFactory;
import com.alex.demo.springboot.starter.factory.ThreadIgnorePolicy;
import com.alex.demo.springboot.starter.factory.VideoReceiveThreadFactory;

/**
 * @Author alex
 * @Created Feb 1, 2021 4:23:38 PM
 * @Description
 *              <p>
 */
@Configuration
public class ThreadPoolAutoConfiguration {

	/**
	 * 空闲线程允许存活的最长时间
	 */
	private long keepAliveTime = 5;

	/**
	 * 任务队列。存储暂时无法执行的任务，等待空闲线程来执行任务
	 */
	private LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(5);// 或者用ArrayBlockingQueue

	private VideoReceiveThreadFactory videoReceiveFactory = new VideoReceiveThreadFactory();

	private MulticastReceiveThreadFactory multicastReceiveFactory = new MulticastReceiveThreadFactory();

	private MessageStorageThreadFactory messageStorageFactory = new MessageStorageThreadFactory();

	/**
	 * 拒绝策略
	 */
	private ThreadIgnorePolicy handler = new ThreadIgnorePolicy();

	// 构造一个视频接收的线程池
	@Bean(name = "videoThreadPool")
	@ConditionalOnClass(ThreadPoolExecutor.class) // 需要项目中存在ThreadPoolExecutor类，因为这是JDK自带的，所以一定存在
	public ThreadPoolExecutor videoReceiveThreadPool() {
		return new ThreadPoolExecutor(5, 7, keepAliveTime, TimeUnit.SECONDS, workQueue, videoReceiveFactory, handler);
	}

	// 构造一个组播接收的线程池
	@Bean
	@ConditionalOnClass(ThreadPoolExecutor.class)
	public ThreadPoolExecutor multicastReceiveThreadPool() {
		return new ThreadPoolExecutor(38, 40, keepAliveTime, TimeUnit.SECONDS, workQueue, multicastReceiveFactory, handler);
	}

	// 构造一个报文存储的线程池
	@Bean
	@ConditionalOnClass(ThreadPoolExecutor.class)
	public ThreadPoolExecutor messageStorageThreadPool() {
		return new ThreadPoolExecutor(10, 12, keepAliveTime, TimeUnit.SECONDS, workQueue, messageStorageFactory, handler);
	}

}
