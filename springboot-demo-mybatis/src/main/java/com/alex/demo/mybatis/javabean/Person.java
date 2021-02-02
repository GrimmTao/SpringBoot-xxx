/*******************************************************************************
 * Copyright (c) 2020, 2020 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.mybatis.javabean;

import java.io.Serializable;

import org.apache.ibatis.type.JdbcType;

import com.alex.demo.mybatis.handler.RoleHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.ColumnType;

/**
 * @Version 1.0
 * @Author Alex
 * @Description
 *              <p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Person extends BaseEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -15520271173077301L;

	private Integer age;

	@ColumnType(jdbcType = JdbcType.INTEGER, typeHandler = RoleHandler.class)
	private Role role;
}
