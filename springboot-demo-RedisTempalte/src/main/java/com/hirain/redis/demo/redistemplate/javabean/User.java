package com.hirain.redis.demo.redistemplate.javabean;

import java.io.Serializable;

import lombok.Data;

/**
 * @Version 1.0
 * @Author zepei.tao@hirain.com
 * @Created Dec 2020/3/20 13:32
 * @Description
 *              <p>
 * @Modification
 *               <p>
 *               Date Author Version Description
 *               <p>
 *               2020/3/20 zepei.tao@hirain.com 1.0 create file
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