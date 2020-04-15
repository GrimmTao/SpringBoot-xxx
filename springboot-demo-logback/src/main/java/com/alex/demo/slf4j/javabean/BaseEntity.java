package com.alex.demo.slf4j.javabean;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * @Author Alex
 * @Created Dec 2020/3/20 13:32
 * @Description
 *              <p>
 */
@Data
public class BaseEntity {

	private Integer id;

	private LocalDateTime createDate;

	private LocalDateTime updateDate;

}