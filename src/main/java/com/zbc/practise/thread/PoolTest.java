package com.zbc.practise.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author StormT1King
 * 为什么阿里发布的 Java开发手册中强制线程池不允许使用 Executors 去创建？
 */
public class PoolTest {
    // 核心线程池的大小
    public static final int CORE_POOL_SIZE = 10;
    // 最大线程池的大小
    public static final int MAXIMUM_POOL_SIZE = 20;
    // 超过核心线程池的大小哪些线程 最多可以存活多久
    public static final long KEEP_ALIVE_TIME = 3000;

    // 创建线程的线程工厂，这个建议一定要自己重写一下，因为可以增加很多关键信息，方便出问题的时候dump或者看日志能定位到问题
    public static ThreadFactory threadFactory;
    // 等待队列
    public static BlockingQueue<Runnable> workQueue;

    // 拒绝策略
    public static RejectedExecutionHandler rejectedExecutionHandler;

    public static void main(String args[]) {
        threadFactory = new MyThreadFactory("wuyuetestfactory");
        // 要启用拒绝策略的前提就是一定得是有界队列，你无界队列可以无限制丢 当然不用care 决绝策略了
        workQueue = new ArrayBlockingQueue(100);
        // 会抛异常 RejectedExecutionException
        rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
        // 不应像主流程，随意丢
        // rejectedExecutionHandler = new ThreadPoolExecutor.DiscardPolicy();
        // 丢最老的。优先丢队头
        // rejectedExecutionHandler = new ThreadPoolExecutor.DiscardOldestPolicy();
        // rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();


        /**
         * 本质上来说 线程池的执行逻辑其实真的很简单：
         * 如果当前线程池的线程个数小于CORE_POOL_SIZE 那么有任务到来的时候 就直接创建一个线程 执行这个任务
         * 如果当前线程池的线程个数已经到了CORE_POOL_SIZE这个极限，那么新来的任务就会被放到workQueue中
         * 如果workQueue里面的任务已满，且MAXIMUM_POOL_SIZE这个值大于CORE_POOL_SIZE，那么此时线程池会继续创建线程执行任务
         * 如果workQueue满了，且线程池的线程数量也已经达到了MAXIMUM_POOL_SIZE 那么就会把任务丢给rejectedExecutionHandler 来处理
         * 当线程池中的线程超过了CORE_POOL_SIZE的哪些线程 如果空闲时间到了KEEP_ALIVE_TIME 那么就会自动销毁
         * 当设置allowCoreThreadTimeOut(true)时，线程池中corePoolSize线程空闲时间达到keepAliveTime也将关闭
         */
        // 默认工厂创建
        // ExecutorService executorService = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS, workQueue, Executors.defaultThreadFactory(), rejectedExecutionHandler);
        ExecutorService executorService = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS, workQueue, threadFactory, rejectedExecutionHandler);

        for (int i = 0; i < Long.MAX_VALUE; i++) {
            Task task = new Task();
            executorService.execute(task);
        }
        // System.out.println(myThreadFactory.getStas());
        /*
        executorService.execute(() => {

        });
        */

    }
}

// 自定义一个线程工厂
class MyThreadFactory implements ThreadFactory {
    int counter = 0;
    String name;
    private List<String> stats;

    public MyThreadFactory(String name) {
        this.name = name;
        stats = new ArrayList<>();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, name + "-Thread-" + counter);
        counter++;
        String logInfo = String.format("Created thread %d with name %s on%s\n", t.getId(), t.getName(), new Date());
        System.out.println(logInfo);
        return t;
    }

    //这个方法的调用时机 就看你们具体业务上需求如何了，其实线程工厂真的很简单，主要就是根据你的环境
    //定制出你需要的信息 方便日后调试即可 不需要太纠结。
    public String getStas() {
        StringBuffer buffer = new StringBuffer();
        Iterator<String> it = stats.iterator();
        while (it.hasNext()) {
            buffer.append(it.next());
        }
        return buffer.toString();
    }

}

class Task implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
