package com.zbc.practise.pattern.singleton;

/**
 * @author StormT1King
 * 单例模式02
 * 懒汉式
 * 优点：需要时才会创建
 * 缺点：多线程情况会有问题。
 */
public class Mannger02 {
    private static Mannger02 INSTANCE;

    private Mannger02() {

    };

    public static Mannger02 getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Mannger02();
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
