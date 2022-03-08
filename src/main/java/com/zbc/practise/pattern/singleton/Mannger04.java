package com.zbc.practise.pattern.singleton;

/**
 * @author StormT1King
 * 单例模式04
 * 静态内部类
 */
public class Mannger04 {

    private Mannger04() {
    };

    private static class Mannger04Holder {
        private static final Mannger04 INSTANCE = new Mannger04();
    }

    public static Mannger04 getInstance() {
        return Mannger04Holder.INSTANCE;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Mannger02.getInstance().hashCode());
            }).start();
        }
    }
}
