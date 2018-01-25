package com.yhq.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yhq.App;
import com.yhq.web.service.TestService;

/**
 * @author HuaQi.Yang
 * @date 2017年8月17日
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class TestDemo {

	private int b = a - 1;
	private static int a = -10;

	// static {
	// System.out.println(a);
	// System.out.println("静态块执行");
	// }
	//
	// {
	// System.out.println(b);
	// System.out.println("动态块执行");
	// b--;
	// }
	//
	// public TestDemo() {
	// System.out.println(b);
	// System.out.println("私有无参构造");
	// }

	@Autowired
	private TestService testService;

	@Test
	public void test() {
		System.out.println(Thread.currentThread().getName());
		synchronized (this) {
			System.out.println("实例同步块：" + Thread.currentThread().getName());
		}
		synchronized (App.class) {
			System.out.println("类同步块：" + Thread.currentThread().getName());
		}
		System.out.println("同步块结束" + Thread.currentThread().getName());
		// for (int i = 0; i < 10; ++i) {
		// testService.batchInsert1(1000000);
		// }

	}

	enum e {
		A, B, C, D;
	}

	public static void main(String[] args) {
		int i = 0;
		short s = 1;
		long l = 2;
		String str = "";
		char c = ' ';
		e e1[] = e.values();
		switch (e1[1]) {
		case A:
			break;
		case B:
			break;
		case C:
			break;
		case D:
			break;
		}
		// List<HashMap> list = new ArrayList<HashMap>();
		// int count = 0;
		// while (true) {
		// list.add(new HashMap());
		// count++;
		// if (count % 30000 == 0) {
		// try {
		// Thread.sleep(2000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// }
		// TestDemo aDemo = new TestDemo();
		// Thread t1 = new Thread(() -> {
		// aDemo.test();
		// });
		// ;
		// Thread t2 = new Thread(() -> {
		// aDemo.test();
		// });
		// ;
		// Thread t3 = new Thread(() -> {
		// aDemo.test();
		// });
		// t1.start();
		// t2.start();
		// t3.start();
	}
}
