/*******************************************************************************
 * Copyright (c) 2020, 2020 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.mybatis.javabean;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

/**
 * @Version 1.0
 * @Author Alex
 * @Description
 *              <p>
 */
public enum Role implements BaseEnum {

	Student(0, "学生"),
	//
	Teacher(1, "老师");

	@Getter
	private Integer code;

	@Getter
	@JsonValue
	private String desc;

	Role(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		System.out.println("-- " + super.toString());
		return "code:" + getCode() + " ,desc:" + getDesc();
	}

}
