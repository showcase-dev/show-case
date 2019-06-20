package com.lwy.rpc.service.impl;

import com.lwy.rpc.service.HelloService;

public class HelloServiceImpl implements HelloService {

	@Override
	public String say(String name) {
		return "hi," + name;
	}

}
