package com.alex.demo.redistemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.alex.demo.redistemplate.dao")
public class RedistemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedistemplateApplication.class, args);
	}

}
