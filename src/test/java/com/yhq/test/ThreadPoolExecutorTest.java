package com.yhq.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class ThreadPoolExecutorTest {
	public static void main(String[] args) {

	}

	final static ThreadLocal<Object[][]> localObjects = new ThreadLocal<>();

	@Test
	public void test() {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 100; i++) {
			final int index = i;
			fixedThreadPool.execute(new Runnable() {
				public void run() {
					if (localObjects.get() == null) {
						Object[][]  objects= new Object[12][3];
						localObjects.set(objects);
					}
					Object[][] objects = localObjects.get();
					System.out.println(Thread.currentThread().getName() + ":" + localObjects.get().hashCode());
					System.out.println(Thread.currentThread().getName() + ":" + objects.hashCode());
					for (Object[] objects2 : objects) {
						System.out.println("次级:" + objects2.hashCode());
						for (int i = 0;i < objects2.length;++i) {
							objects2[i] = objects.hashCode() + objects2.hashCode()+"-";
							System.out.println("末级：" + objects2[i]);
						}
					}
				}
			});
		}
	}
}