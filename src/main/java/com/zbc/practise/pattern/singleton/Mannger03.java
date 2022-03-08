package com.zbc.practise.pattern.singleton;

/**
 * @author StormT1King
 * 单例模式03
 * 懒汉式
 * 优点：解决多线程情况的问题
 * 缺点：每次获取对象都要获取锁降低效率，static锁整个对象
 */
public class Mannger03 {
    private static Mannger03 INSTANCE;

    private Mannger03() {

    };

    public static synchronized Mannger03 getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Mannger03();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {
            /*new Thread(new Runnable() {
                @Override
                public void run() {
                     System.out.println(Mannger02.getInstance().hashCode());
                }
            }).start();*/

            // 多线程情况会出现创建多个对象。
            new Thread(() -> {
                System.out.println(Mannger02.getInstance().hashCode());
            }).start();
        }
    }
}
