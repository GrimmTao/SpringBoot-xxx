package com.alex.demo.aop.aop;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alex.demo.aop.annotation.LogAnnotation;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogAspect {

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
	@Pointcut("@annotation(com.alex.demo.aop.annotation.LogAnnotation)")
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
	@Pointcut("execution(public * com.alex.demo.aop.controller.AopController.*(..))")
	public void executeControllerMethod() {
	}

	@Pointcut("execution(public * com.alex.demo.aop.controller.AopController.doWithException*(..))")
	public void executeExceptionCatch() {
	}

	/**
	 * Pointcut的表达式中可使用使用 &&、||、！ 运算符
	 * 如下，cutAll()的作用等同于 executeMethod 和 controllerTwoLog 之和
	 */
	@Pointcut("executeAnnotationMethod() && executeControllerMethod()")
	public void cutAll() {
	}

	/**
	 * 带参数切点
	 * 
	 * @param name
	 * @param age
	 */
	@Pointcut("execution(public * com.alex.demo.aop.controller.AopController.testParam(String,int)) && args(name,age))")
	public void paramAspect(String name, int age) {
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
		String methodName = jp.getSignature().getName();
		System.out.println("【前置增强】the method 【" + methodName + "】 begins with " + JSON.toJSONString(jp.getArgs()));

		/*
		 * RequestContextHolder，请求前会把HttpServletRequest放入RequestContextHolder
		 * RequestContextHolder的原因是threadLocal的应用,它是与当前线程进行绑定，所以无论何时都能获取到
		 * 请求结束后会把里面的HttpServletRequest移除
		 */
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		System.out.println("请求地址：" + request.getRequestURL());
		System.out.println("请求IP:" + request.getRemoteAddr());
		System.out.println("请求方法：" + request.getMethod());
		System.out.println("请求入参：" + JSONObject.toJSONString(request.getParameterMap()));
	}

	/**
	 * 后置增强：目标方法执行之后执行以下方法体的内容，不管是否发生异常。
	 *
	 * @param jp
	 */
	@After("executeAnnotationMethod() || executeControllerMethod()")
	public void afterMethod(JoinPoint jp) throws IOException {
		System.out.println("【后置增强】this is a afterMethod advice..." + JSON.toJSONString(jp.getArgs()));

		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = servletRequestAttributes.getResponse();
		System.out.println("响应参数：" + response.getOutputStream().toString());
	}

	/**
	 * 返回增强：目标方法正常执行完毕时执行
	 *
	 * @param jp
	 * @param result
	 */
	@AfterReturning(value = "executeAnnotationMethod()", returning = "result")
	public void afterReturningMethod(JoinPoint jp, Object result) {
		String methodName = jp.getSignature().getName();
		System.out.println("【返回增强】the method 【" + methodName + "】 ends with 【" + result + "】");
	}

	/**
	 * 异常增强：目标方法发生异常的时候执行，第二个参数表示补货异常的类型
	 *
	 * @param jp
	 * @param e
	 */
	@AfterThrowing(value = "executeExceptionCatch()", throwing = "e")
	public void afterThorwingMethod(JoinPoint jp, Exception e) {
		String methodName = jp.getSignature().getName();
		System.err.println("【异常增强】the method 【" + methodName + "】 occurs exception: " + e.getMessage());
	}

	/**
	 * 环绕增强：目标方法执行前后分别执行一些代码，发生异常的时候执行另外一些代码
	 *
	 * @return
	 */
	@Around(value = "executeAnnotationMethod()")
	public Object aroundMethod(ProceedingJoinPoint jp) {
		String methodName = jp.getSignature().getName();
		Object result;
		try {
			// 执行目标方法
			System.out.println("proceed之前");
			result = jp.proceed();
			System.out.println("proceed之后");
			MethodSignature signature = (MethodSignature) jp.getSignature();
			Method method = signature.getMethod();
			LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
			if (logAnnotation != null) {
				// 注解上的描述
				System.out.println("Log描述：" + logAnnotation.logDesc());
			}
		} catch (Throwable e) {
			result = "error";
		}
		return result;
	}

	@Around(value = "paramAspect(name,age)")
	public Object aroundMethod(ProceedingJoinPoint jp, String name, int age) {
		Object result;
		try {
			// 执行目标方法
			result = jp.proceed();
			System.out.println("name:" + name + "  age:" + age);
		} catch (Throwable e) {
			result = "error";
		}
		System.out.println("result:" + result);
		return result;
	}

}