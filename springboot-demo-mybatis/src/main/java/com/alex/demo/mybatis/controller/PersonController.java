/*******************************************************************************
 * Copyright (c) 2020, 2020 Alex.
 ******************************************************************************/
package com.alex.demo.mybatis.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alex.demo.mybatis.dao.PersonMapper;
import com.alex.demo.mybatis.javabean.Person;

/**
 * @Author Alex
 * @Created Dec 2020/3/27 16:05
 * @Description
 *              <p>
 */
@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonMapper personMapper;

	@GetMapping("/getAllPerson")
	public List<Person> getAllPerson() {
		List<Person> allPerson = personMapper.listPersons();
		System.out.println(allPerson.size());
		return allPerson;
	}

	@PostMapping("/insertPerson")
	public void personInsert(@RequestBody Person person) {
		LocalDateTime now = LocalDateTime.now();
		person.setCreateDate(now);
		person.setUpdateDate(now);
		personMapper.insertPerson(person);
	}

}
