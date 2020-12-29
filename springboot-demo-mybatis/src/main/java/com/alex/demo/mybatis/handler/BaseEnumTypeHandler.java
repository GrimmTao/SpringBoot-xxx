package com.alex.demo.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import com.alex.demo.mybatis.javabean.BaseEnum;

/**
 * @Version 1.0
 * @Author Alex
 * @Description
 *              <p>
 */
@MappedTypes({ BaseEnum.class }) // 转换后的数据类型
@MappedJdbcTypes({ JdbcType.INTEGER }) // 数据库中的数据类型
public class BaseEnumTypeHandler<E extends BaseEnum> extends BaseTypeHandler<BaseEnum> {

	private Class<E> clazz;

	public BaseEnumTypeHandler(Class<E> enumType) {
		if (enumType == null) {
			throw new IllegalArgumentException("Type argument cannot be null");
		}
		this.clazz = enumType;
	}

	@Override
	public void setNonNullParameter(PreparedStatement preparedStatement, int i, BaseEnum windLabEnum, JdbcType jdbcType) throws SQLException {
		preparedStatement.setInt(i, windLabEnum.getCode());
	}

	@Override
	public BaseEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
		return getByType(resultSet.getInt(s));
	}

	@Override
	public BaseEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
		return getByType(resultSet.getInt(i));
	}

	@Override
	public BaseEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
		return getByType(callableStatement.getInt(i));
	}

	private BaseEnum getByType(int anInt) {
		final E[] enumConstants = clazz.getEnumConstants();
		for (BaseEnum enums : enumConstants) {
			if (enums.getCode() == anInt) {
				return enums;
			}
		}
		return null;
	}

}