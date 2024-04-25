package com.zbc.practise.meituan;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 使用CompletableFuture编排 AService.get()、BService.get()、CService.get(int i) （ABC三个服务返回结果都是int），满足使用A、B的结果相加，再作为参数传给C，取得C的结果后乘以 100 后返回。
 * 要求：
 * 1.显示指定线程池
 * 2.处理异常情况，异常情况返回0
 */
public class Test {

    public static void main(String[] args) {
        // 固定长度线程池
        Executor executor = Executors.newFixedThreadPool(3);

        // 异步调用A服务获取结果集
        CompletableFuture<Integer> aFuture = CompletableFuture.supplyAsync(() -> AService.get(), executor)
                .exceptionally(e -> {
                    System.out.println("A服务异常: " + e);
                    return 0;
                });

        // 异步调用B服务获取结果集
        CompletableFuture<Integer> bFuture = CompletableFuture.supplyAsync(() -> BService.get(), executor)
                .exceptionally(e -> {
                    System.out.println("B服务异常: " + e);
                    return 0;
                });

        // AB服务线程池执行完后对结果集合并并处理
        aFuture.thenCombine(bFuture, (a, b) -> a + b)
                // 得到返回结果作为下一个节点的参数
                .thenCompose(sum -> CompletableFuture.supplyAsync(() -> CService.get(sum), executor))
                .exceptionally(e -> {
                    System.out.println("C服务异常: " + e);
                    return 0;
                })
                .thenAccept(finalResult -> System.out.println("最终结果：" + finalResult));
    }

}


class AService {
    public static int get() {
        return 10;
    }
}

class BService {
    public static int get() {
        return 20;
    }
}

class CService {
    public static int get(int i) {
        return i * 100;
    }
}
