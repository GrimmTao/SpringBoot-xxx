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

	@Bean
	public VideoReceiveThreadFactory videoReceiveFactory() {
		return new VideoReceiveThreadFactory();
	}

	@Bean
	public MulticastReceiveThreadFactory multicastReceiveFactory() {
		return new MulticastReceiveThreadFactory();
	}

	@Bean
	public MessageStorageThreadFactory messageStorageFactory() {
		return new MessageStorageThreadFactory();
	}

	/**
	 * 拒绝策略
	 */
	@Bean
	public ThreadIgnorePolicy rejectHandler() {
		return new ThreadIgnorePolicy();
	}

	/**
	 * 构造一个视频接收的线程池
	 * 
	 * @param videoReceiveFactory
	 *            参数名尽量和上面的videoReceiveFactory()方法同名，这样才能映射到
	 *            因为本例中只有一个自动加载的VideoReceiveThreadFactory对象，所以参数名和方法名不相同也没关系
	 *            但如果说有多个自动加载的VideoReceiveThreadFactory对象，则必须通过名称来区别
	 * @param rejectHandler
	 *            参数名尽量和上面的rejectHandler()方法同名
	 */
	@Bean(name = "videoThreadPool")
	@ConditionalOnClass(ThreadPoolExecutor.class) // 需要项目中存在ThreadPoolExecutor类，因为这是JDK自带的，所以一定存在
	public ThreadPoolExecutor videoReceiveThreadPool(VideoReceiveThreadFactory videoReceiveFactory, ThreadIgnorePolicy rejectHandler) {
		return new ThreadPoolExecutor(5, 7, keepAliveTime, TimeUnit.SECONDS, workQueue, videoReceiveFactory, rejectHandler);
	}

	/**
	 * 构造一个组播接收的线程池
	 * 
	 * @param multicastReceiveFactory
	 *            参数名尽量和上面的multicastReceiveFactory()同名
	 * @param rejectHandler
	 *            参数名尽量和上面的rejectHandler()方法同名
	 */
	@Bean
	@ConditionalOnClass(ThreadPoolExecutor.class)
	public ThreadPoolExecutor multicastReceiveThreadPool(MulticastReceiveThreadFactory multicastReceiveFactory, ThreadIgnorePolicy rejectHandler) {
		return new ThreadPoolExecutor(38, 40, keepAliveTime, TimeUnit.SECONDS, workQueue, multicastReceiveFactory, rejectHandler);
	}

	/**
	 * 构造一个报文存储的线程池
	 * 
	 * @param messageStorageFactory
	 *            参数名尽量和上面的messageStorageFactory()方法同名
	 * @param rejectHandler
	 *            参数名尽量和上面的rejectHandler()方法同名
	 */
	@Bean
	@ConditionalOnClass(ThreadPoolExecutor.class)
	public ThreadPoolExecutor messageStorageThreadPool(MessageStorageThreadFactory messageStorageFactory, ThreadIgnorePolicy rejectHandler) {
		return new ThreadPoolExecutor(10, 12, keepAliveTime, TimeUnit.SECONDS, workQueue, messageStorageFactory, rejectHandler);
	}

}
