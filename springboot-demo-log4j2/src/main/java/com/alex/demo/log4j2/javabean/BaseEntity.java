package com.alex.demo.log4j2.javabean;

import java.time.LocalDateTime;

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
public class BaseEntity {

	private Integer id;

	private LocalDateTime createDate;

	private LocalDateTime updateDate;

}