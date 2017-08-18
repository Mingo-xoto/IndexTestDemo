package com.yhq.IndexTestDemo.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yhq.IndexTestDemo.service.TestService;

/**
 * @author HuaQi.Yang
 * @date 2017年8月18日
 */
@RestController
@RequestMapping("/test/")
public class TestController {

	@Autowired
	private TestService testService;

	@RequestMapping("batch/insert")
	public void testBatchInsert(int length, int loop) {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		for (int i = 0; i <= loop; ++i) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + "开始");
					testService.batchInsert(length);
					System.out.println(Thread.currentThread().getName() + "结束");
				}
			});
		}
	}
}
