package com.yhq.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yhq.IndexTestDemo.App;
import com.yhq.IndexTestDemo.service.TestService;

/**
 * @author HuaQi.Yang
 * @date 2017年8月17日
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class TestDemo {

	@Autowired
	private TestService testService;

	@Test
	public void test() {
		testService.batchInsert();
	}
}
