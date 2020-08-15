package com.alex.demo.redistemplate.controller;

import org.springframework.ui.ModelMap;

public abstract class BaseController {

	// protected final String success = StateParameter.SUCCESS;

	// protected final String fail = StateParameter.FAULT;

	// protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public ModelMap getModelMap(String status, Object data, String msg) {
		ModelMap modelMap = new ModelMap();
		modelMap.put("status", status);
		modelMap.put("data", data);
		modelMap.put("msg", msg);
		return modelMap;
	}

}