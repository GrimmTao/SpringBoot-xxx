/*******************************************************************************
 * Copyright (c) 2020, 2020 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.mybatis.javabean;

import java.io.Serializable;

import org.apache.ibatis.type.JdbcType;

import com.alex.demo.mybatis.handler.RoleHandler;

import lombok.Data;
import tk.mybatis.mapper.annotation.ColumnType;

/**
 * @Version 1.0
 * @Author Alex
 * @Description
 *              <p>
 */
@Data
public class Person extends BaseEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -15520271173077301L;

	private Integer age;

	@ColumnType(jdbcType = JdbcType.INTEGER, typeHandler = RoleHandler.class)
	private Role role;
}
