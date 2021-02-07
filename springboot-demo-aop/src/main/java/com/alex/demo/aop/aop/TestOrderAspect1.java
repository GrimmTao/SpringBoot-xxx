/*******************************************************************************
 * Copyright (c) 2021, 2021 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.aop.aop;

import java.io.IOException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author alex
 * @Created Dec 2021/2/5 18:59
 * @Description
 *              <p>
 * @Modification
 *               <p>
 */
@Component
@Slf4j
@Aspect
@Order(1)
public class TestOrderAspect1 {

	/**
	 * execution: 匹配连接点
	 * within: 某个类里面
	 * this: 指定AOP代理类的类型
	 * target:指定目标对象的类型
	 * args: 指定参数的类型
	 * bean:指定特定的bean名称，可以使用通配符（Spring自带的）
	 * @target： 带有指定注解的类型
	 *
	 * @args: 指定运行时传的参数带有指定的注解
	 * @within: 匹配使用指定注解的类
	 * @annotation:指定方法所应用的注解
	 */
	@Pointcut("@annotation(com.alex.demo.aop.annotation.AopAnnotation)")
	public void executeAnnotationMethod() {
	}

	/**
	 * execution（方法类型 方法的返回值类型 包路径（可省略） 方法的名称（参数） 异常类型（可省略）)
	 * 方法类型,Public，Protected等(可选,即可填可不填);
	 * 方法返回值类型,*表示任何返回值;
	 * 包路径;
	 * 方法名称（参数）,*代表所有,set*,代表以set开头的所有方法。()匹配没有参数、(..)代表任意多个参数、(*)代表一个参数，但可以是任意型、(*,String)代表第一个参数为任何值,第二个为String类型;
	 * 异常类型，可省略；
	 */
	@Pointcut("execution(public * com.alex.demo.aop.controller.OrderController.*(..))")
	public void executeControllerMethod() {
	}

	@Pointcut("execution(public * com.alex.demo.aop.controller.OrderController.doWithException*(..))")
	public void executeExceptionCatch() {
	}

	/**
	 * 前置增强：目标方法执行之前执行
	 * 除了注解@Around的方法外，其他都可以加这个JoinPoint作参数。@Around注解的方法的参数一定要是ProceedingJoinPoint
	 *
	 * @param jp
	 *            Object[] getArgs：返回目标方法的参数
	 *            Signature getSignature：返回目标方法的签名
	 *            Object getTarget：返回被织入增强处理的目标对象
	 *            Object getThis：返回AOP框架为目标对象生成的代理对象
	 */
	@Before("executeAnnotationMethod() || executeControllerMethod()")
	public void beforeMethod(JoinPoint jp) {
		System.out.println("执行Aspect1的Before");
	}

	/**
	 * 后置增强：目标方法执行之后执行以下方法体的内容，不管是否发生异常。
	 *
	 * @param jp
	 */
	@After("executeAnnotationMethod() || executeControllerMethod()")
	public void afterMethod(JoinPoint jp) throws IOException {
		System.out.println("执行Aspect1的After");
	}

	/**
	 * 返回增强：目标方法正常执行完毕时执行
	 *
	 * @param jp
	 * @param result
	 */
	@AfterReturning(value = "executeAnnotationMethod()", returning = "result")
	public void afterReturningMethod(JoinPoint jp, Object result) {
		System.out.println("执行Aspect1的AfterReturning");
	}

	/**
	 * 异常增强：目标方法发生异常的时候执行，第二个参数表示捕获异常的类型
	 *
	 * @param jp
	 * @param e
	 *            捕获异常的类型
	 */
	@AfterThrowing(value = "executeExceptionCatch()", throwing = "e")
	public void afterThorwingMethod(JoinPoint jp, Exception e) {
		System.out.println("执行Aspect1的AfterThrowing");
	}

	/**
	 * 环绕增强：目标方法执行前后分别执行一些代码，发生异常的时候执行另外一些代码
	 *
	 * @return
	 */
	@Around(value = "executeAnnotationMethod() || executeControllerMethod()")
	public Object aroundMethod(ProceedingJoinPoint jp) {
		Object result;
		try {
			// 执行目标方法
			System.out.println("Aspect1 proceed之前");
			result = jp.proceed();
			System.out.println("Aspect1 proceed之后");
		} catch (Throwable e) {
			result = "error";
		}
		return result;
	}

}
