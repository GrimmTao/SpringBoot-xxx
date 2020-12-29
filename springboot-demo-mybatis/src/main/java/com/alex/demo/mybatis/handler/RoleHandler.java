/*******************************************************************************
 * Copyright (c) 2020, 2020 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.mybatis.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import com.alex.demo.mybatis.javabean.Role;

/**
 * @Version 1.0
 * @Author Alex
 * @Description
 *              <p>
 */
@MappedTypes({ Role.class }) // 转换后的数据类型
@MappedJdbcTypes({ JdbcType.INTEGER }) // 数据库中的数据类型
public class RoleHandler extends BaseEnumTypeHandler<Role> {

	public RoleHandler(Class<Role> type) {
		super(type);
	}
}
