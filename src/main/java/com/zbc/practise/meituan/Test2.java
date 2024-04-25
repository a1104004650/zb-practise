package com.zbc.practise.meituan;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 编写一个队列，生产者生产消息（消息内容是1,2,3,...,n），消费者消费消息，并将消息打印到控制台。
 * 要求：
 *
 * 生产者以每秒10个的速率生产消息，队列满了后阻塞等待；
 * 队列长度为100；
 * 消费者以每秒1个的速率消费消息；
 * 生产者和消费者在不同的线程；
 */
public class Test2 {

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
