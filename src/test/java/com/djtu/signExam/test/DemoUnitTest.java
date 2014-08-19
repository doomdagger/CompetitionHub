package com.djtu.signExam.test;

import org.junit.Test;

import com.djtu.signExam.config.ProjectConfig;

public class DemoUnitTest {
	@Test
	public void demoTest(){
		System.err.println(ProjectConfig.getProperty("fake.path"));;
	}
}
