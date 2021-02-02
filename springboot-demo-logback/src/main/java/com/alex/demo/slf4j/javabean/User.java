/*******************************************************************************
 * Copyright (c) 2020, 2020 Alex.
 ******************************************************************************/
package com.alex.demo.slf4j.javabean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author Alex
 * @Created Dec 2020/3/27 16:01
 * @Description
 *              <p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity {

	private String name;

	private Integer age;

	@Override
	public String toString() {
		return "name: " + name + "  age: " + age;
	}
}
