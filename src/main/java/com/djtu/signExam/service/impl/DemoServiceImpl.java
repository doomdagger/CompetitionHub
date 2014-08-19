package com.djtu.signExam.service.impl;

import org.springframework.stereotype.Component;

import com.djtu.signExam.service.DemoService;

@Component("demoService")
public class DemoServiceImpl implements DemoService{

	@Override
	public String greeting(String salutation) {
		return "Lihe Say: " + salutation;
	}

	@Override
	public boolean areYouGood() {
		throw new RuntimeException("I am not OK!!!");
	}

}
