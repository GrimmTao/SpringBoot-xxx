package com.alex.demo.aop.annotation;

import java.lang.annotation.*;

/**
 * @Author Alex
 * @Created Dec 2020/3/27 17:00
 * @Description
 *              <p>
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {

	String desc = "输入日志信息";

	String logDesc() default desc;

}
