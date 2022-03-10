package com.zbc.practise.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author StormT1King
 * 线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。 说明：Executors返回的线程池对象的弊端如下：
 * 1）FixedThreadPool和SingleThreadPool:
 * 允许的请求队列长度为Integer.MAX_VALUE，可能会堆积大量的请求，从而导致OOM。
 * 2）CachedThreadPool:
 * 允许的创建线程数量为Integer.MAX_VALUE，可能会创建大量的线程，从而导致OOM。
 */
public class BaseService {

	private final static ExecutorService executor = Executors.newFixedThreadPool(100);// 启用多线程
	final static List<Integer> last = new ArrayList<Integer>();

	public static void main(String[] args) throws InterruptedException {
		List<Integer> df = new ArrayList<Integer>();
		for (int i = 0; i < 10000; i++) {
			df.add(i);
		}
		

		for (Integer integer : df) {
			executor.execute(new Runnable() {

				@Override
				public void run() {
					if(integer % 2 == 0) {
						//last.add(integer);
						add(integer);
					}
				}
				
			});
		}
		
		executor.shutdown();
		while(!executor.isTerminated()) {
   		 	Thread.sleep(3000);
		}
		
		System.out.println(last);
		System.out.println(last.size());
	}
	
	public synchronized static void add(Integer integer) {
		last.add(integer);
	}
	
}
