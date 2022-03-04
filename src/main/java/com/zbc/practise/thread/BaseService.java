package com.zbc.practise.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
