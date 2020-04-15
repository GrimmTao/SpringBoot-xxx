package com.alex.demo.jpa.javabean;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Data;

/**
 * @Author Alex
 * @Created Dec 2020/3/20 13:32
 * @Description
 *              <p>
 */
/**
 * 1.@MappedSuperclass注解只能标注在类上
 * 2.标注为@MappedSuperclass的类将不会映射到数据库表，但是他的属性都将映射到其子类的数据库字段中。
 * 3.标注为@MappedSuperclass的类不能再标注@Entity或@Table注解，也无需实现序列化接口。
 * 4.但是如果一个标注为@MappedSuperclass的类继承了另外一个实体类或者另外一个同样标注了@MappedSuperclass的类的话，
 * 他将可以使用@AttributeOverride或@AttributeOverrides注解重定义其父类(无论是否是实体类)的属性映射到数据库表中的字段。
 * 比如可以重定义字段名或长度等属性，使用@AttributeOverride中的子属性@Column进行具体的定义。
 */
@Data
@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增主键
	@Column(name = "id")
	private Integer id;

	@Column(name = "create_date") // 创建时间
	private LocalDateTime createDate;

	@Column(name = "update_date") // 修改时间
	private LocalDateTime updateDate;

}