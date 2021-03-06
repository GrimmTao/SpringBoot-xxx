/*******************************************************************************
 * Copyright (c) 2021, 2021 Hirain Technologies Corporation.
 ******************************************************************************/
package com.alex.demo.springboot.starter.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author alex
 * @Created Feb 2, 2021 10:58:16 AM
 * @Description
 *              <p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

	private String cityName;

	private Integer cityCode;

}
