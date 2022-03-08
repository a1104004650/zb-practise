package com.zbc.practise.pattern.singleton;

/**
 * @author StormT1King
 * 单例模式01
 * 优点：多线程访问同一个对象
 * 缺点：无论如何都会创建一个对象加载到内存中
 */
public class Mannger01 {
    private static final Mannger01 INSTANCE = new Mannger01();

    private Mannger01() {

    };

    public static Mannger01 getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        // 01例子
        Mannger01 m01 = Mannger01.getInstance();
        Mannger01 m02 = Mannger01.getInstance();
        System.out.print(m01 == m02);
    }

}
