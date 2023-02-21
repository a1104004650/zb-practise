package com.zbc.practise.doubleball.test;

import com.zbc.practise.doubleball.service.DoubleBallService;

import java.util.Map;

public class BallTest {

    public static void main(String[] args) {
        DoubleBallService start = new DoubleBallService();
        long startTime = System.currentTimeMillis();
        start.printBall(start.randomBall(6, 1));
        System.out.println("程序运行时间：" + (System.currentTimeMillis() - startTime) + "ms");


    }
}
