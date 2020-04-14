/*******************************************************************************
 * Copyright (c) 2020, 2020 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.jpa.javabean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

/**
 * @Version 1.0
 * @Author zepei.tao@hirain.com
 * @Created Dec 2020/3/27 16:01
 * @Description
 *              <p>
 * @Modification
 *               <p>
 *               Date Author Version Description
 *               <p>
 *               2020/3/27 zepei.tao@hirain.com 1.0 create file
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
