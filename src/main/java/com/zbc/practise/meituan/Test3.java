package com.zbc.practise.meituan;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 在2的基础上，要求：
 * 1.	起20个消费者线程；
 * 2.	消费者取不到消息的时候阻塞等待；
 * 3.	不能重复消费；
 */
public class Test3 {

    private static final int QUEUE_LENGTH = 100;

    public static void main(String[] args) {
        // 初始化管道
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(QUEUE_LENGTH);
        // 生产者
        ExecutorService producerExecutor = Executors.newSingleThreadExecutor();
        // 消费者
        ExecutorService consumerExecutor = Executors.newSingleThreadExecutor();

        producerExecutor.execute(() -> {
            try {
                for (int i = 1; ; i++) {
                    queue.put(i);
                    System.out.println("消息存入队列： " + i);
                    // 生产者每秒10个消息
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // 20个消费者
        for (int i = 0; i < 20; i++) {
            consumerExecutor.execute(() -> {
                try {
                    while (true) {
                        int message = queue.take();
                        System.out.println("消费消息: " + message);
                        // 消费者每秒消费一个消息
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
    }
}
