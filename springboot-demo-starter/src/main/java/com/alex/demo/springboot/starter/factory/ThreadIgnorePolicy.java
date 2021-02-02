/*******************************************************************************
 * Copyright (c) 2020, 2020 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.springboot.starter.factory;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author alex
 * @Created Dec Jul 2, 2020 3:47:50 PM
 * @Description
 *              <p>
 */
public class ThreadIgnorePolicy implements RejectedExecutionHandler {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * @see java.util.concurrent.RejectedExecutionHandler#rejectedExecution(java.lang.Runnable, java.util.concurrent.ThreadPoolExecutor)
	 */
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		logThreadPoolInfo(executor);
		log.warn(executor.getThreadFactory() + " rejected");
	}

	private void logThreadPoolInfo(ThreadPoolExecutor executor) {
		log.warn("poolSize:" + executor.getPoolSize());
		log.warn("corePoolSize:" + executor.getCorePoolSize());
		log.warn("maxPoolSize:" + executor.getMaximumPoolSize());
		log.warn("largestPoolSize:" + executor.getLargestPoolSize());
		log.warn("completedTaskCount:" + executor.getCompletedTaskCount());
		log.warn("taskCount:" + executor.getTaskCount());
		log.warn("activeCount:" + executor.getActiveCount());
		log.warn("keepAliveTime:" + executor.getKeepAliveTime(TimeUnit.SECONDS));
	}

}
