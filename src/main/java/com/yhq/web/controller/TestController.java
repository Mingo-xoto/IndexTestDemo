package com.yhq.web.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yhq.web.service.TestService;

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

	public TestService getTestService() {
		return testService;
	}

	{
		System.out.println("动态块");
	}

	public void setTestService(TestService testService) {
		System.out.println("设置testService");
		this.testService = testService;
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public void setExecutorService(ExecutorService executorService) {
		System.out.println("设置executorService");
		this.executorService = executorService;
	}

	@RequestMapping(value = "testAop1")
	public void testAop1() {
		System.out.println("执行controller");
		testService.test1();
	}

	@RequestMapping(value = "testAop2")
	public void testAop2() {
		System.out.println("执行controller");
		testService.test2();
	}

	@RequestMapping(value = "batch/insert1")
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
							try {
								testService.batchInsert4(length);
							} finally {
								long end = System.currentTimeMillis();
								// System.out.println("batchInsert4：" +
								// Thread.currentThread().getName() + "插入" +
								// length
								// +
								// "条,耗时："
								// + (end - begin) + "豪秒");
								// 每个线程
								doneThred[index] = true;
							}
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
				int cout = 0;
				while (size > 0) {
					int ts = size;
					// 循环遍历前一次未完成线程下标列表
					for (int i = 0; i < size; ++i) {
						// 逐渐移除已经完成工作的线程下标
						Integer index = doneIndexs.get(i);
						if (doneThred[index]) {
							doneIndexs.remove(index);
							size--;
						}
					}
					if (ts == size) {
						System.out.println(cout + "-->当前为完成线程数:" + ts);
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				long end = System.currentTimeMillis();
				System.out.println("\n总计耗时：" + (end - mid));
				System.gc();
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
