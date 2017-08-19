package com.yhq.IndexTestDemo.web.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yhq.IndexTestDemo.web.service.TestService;

/**
 * @author HuaQi.Yang
 * @date 2017年8月18日
 */
@RestController
@RequestMapping("/test/")
public class TestController {

	@Autowired
	private TestService testService;

	@Autowired
	private ExecutorService executorService;

	@RequestMapping("batch/insert1")
	public void testBatchInsert1(int length, int loop) {
		executorService.execute(new Runnable() {
			public void run() {
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
		});
	}

	@RequestMapping("batch/insert2")
	public void testBatchInsert2(int length, int loop) {

		executorService.execute(new Runnable() {
			public void run() {
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
		});
	}

	@RequestMapping("batch/insert3")
	public void testBatchInsert3(int length, int loop) {
		executorService.execute(new Runnable() {
			public void run() {
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
		});
	}

	@RequestMapping("batch/insert4")
	public void testBatchInsert4(int length, int loop) {
		executorService.execute(new Runnable() {
			public void run() {
				boolean[] doneThred = new boolean[loop];
				for (int i = 0; i < loop; ++i) {
					final int index = i;
					executorService.execute(new Runnable() {
						@Override
						public void run() {
							long begin = System.currentTimeMillis();
							testService.batchInsert4(length);
							long end = System.currentTimeMillis();
							// System.out.println("batchInsert4：" +
							// Thread.currentThread().getName() + "插入" + length
							// +
							// "条,耗时："
							// + (end - begin) + "豪秒");
							// 每个线程
							doneThred[index] = true;
						}
					});
				}

				long mid = System.currentTimeMillis();
				List<Integer> doneIndexs = new LinkedList<>();
				// 初始化未完成线程下标列表
				for (int i = 0; i < doneThred.length; i++) {
					if (doneThred[i]) {
						continue;
					}
					doneIndexs.add(i);
				}
				int size = doneIndexs.size();
				System.out.println("未完成工作的线程数：" + size);
				while (size > 0) {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// 循环遍历前一次未完成线程下标列表
					for (int i = 0; i < size; ++i) {
						// 逐渐移除已经完成工作的线程下标
						Integer index = doneIndexs.get(i);
						if (doneThred[index]) {
							doneIndexs.remove(index);
							size--;
						}
					}
				}
				long end = System.currentTimeMillis();
				System.out.println("\n总计耗时：" + (end - mid));
			}
		});
	}

	@RequestMapping("batch/insert5")
	public void testBatchInsert5(int length, int loop) {
		executorService.execute(new Runnable() {
			public void run() {
				long begin = System.currentTimeMillis();
				for (int i = 0; i < loop; ++i) {
					testService.batchInsert4(length);
				}
				long end = System.currentTimeMillis();
				System.out.println("总计耗时：" + (end - begin));
			}
		});
	}
}
