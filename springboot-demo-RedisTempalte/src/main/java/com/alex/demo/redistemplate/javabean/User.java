package com.alex.demo.redistemplate.javabean;

import java.io.Serializable;

import lombok.Data;

/**
 * @Author Alex
 * @Created Dec 2020/3/20 13:32
 * @Description
 *              <p>
 */
@Data
public class User extends BaseEntity implements Serializable {

	private String name;

	private Integer age;

	@Override
	public String toString() {
		return "name: " + name + "  age: " + age;
	}

}