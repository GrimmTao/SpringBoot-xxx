/*******************************************************************************
 * Copyright (c) 2020, 2020 Alex.
 ******************************************************************************/
package com.alex.demo.jpa.javabean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

/**
 * @Author Alex
 * @Created Dec 2020/3/27 16:01
 * @Description
 *              <p>
 */
@Entity // 告诉JPA这是一个实体类（和数据表映射的类）
@Table(name = "user") // @Table来指定和哪个数据表对应;如果省略默认表名就是user；
@Data
public class User extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private Integer age;

	@Override
	public String toString() {
		return "name: " + name + "  age: " + age;
	}
}
