/*******************************************************************************
 * Copyright (c) 2020, 2020 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.springboot.starter.factory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author alex
 * @Created Dec Jul 2, 2020 3:47:50 PM
 * @Description
 *              <p>
 */
@Slf4j
public class VideoReceiveThreadFactory implements ThreadFactory {

	private final AtomicInteger threadNum = new AtomicInteger(1);

	/**
	 * @see java.util.concurrent.ThreadFactory#newThread(java.lang.Runnable)
	 */
	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, "videoreceive_thread-" + threadNum.getAndIncrement());
		log.info(t.getName() + " has been created");
		return t;
	}

}
