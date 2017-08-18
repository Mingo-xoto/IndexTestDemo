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

	@RequestMapping("batch/insert1")
	public void testBatchInsert1(int length, int loop) {
		ExecutorService executorService = Executors.newFixedThreadPool(loop);
		for (int i = 0; i < loop; ++i) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + "开始");
					testService.batchInsert1(length);
					System.out.println(Thread.currentThread().getName() + "结束");
				}
			});
		}
	}

	@RequestMapping("batch/insert2")
	public void testBatchInsert2(int length, int loop) {
		ExecutorService executorService = Executors.newFixedThreadPool(loop);
		for (int i = 0; i < loop; ++i) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + "开始");
					testService.batchInsert2(length);
					System.out.println(Thread.currentThread().getName() + "结束");
				}
			});
		}
	}

	@RequestMapping("batch/insert3")
	public void testBatchInsert3(int length, int loop) {
		ExecutorService executorService = Executors.newFixedThreadPool(loop);
		for (int i = 0; i < loop; ++i) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + "开始");
					testService.batchInsert3(length);
					System.out.println(Thread.currentThread().getName() + "结束");
				}
			});
		}
	}
}
