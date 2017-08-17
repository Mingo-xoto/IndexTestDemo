package com.yhq.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
		// ExecutorService executorService = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 10; ++i) {
			// executorService.execute(new Runnable() {
			// @Override
			// public void run() {
			System.out.println(Thread.currentThread().getName() + "开始");
			testService.batchInsert();
			System.out.println(Thread.currentThread().getName() + "结束");
			// }
			// });
		}
		while (true) {

		}
	}
}
