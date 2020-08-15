/*******************************************************************************
 * Copyright (c) 2020, 2020 Alex.
 ******************************************************************************/
package com.alex.demo.log4j2.javabean;

import lombok.Data;

/**
 * @Author Alex
 * @Created Dec 2020/3/27 16:01
 * @Description
 *              <p>
 */
@Data
public class User extends BaseEntity {

	private String name;

	private Integer age;

	@Override
	public String toString() {
		return "name: " + name + "  age: " + age;
	}
}
