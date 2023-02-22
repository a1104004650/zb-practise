package com.zbc.practise.doubleball.test;

import com.zbc.practise.doubleball.dto.PrizeBall;
import com.zbc.practise.doubleball.service.DoubleBallService;
import com.zbc.practise.doubleball.service.WinGrade;

import java.util.HashMap;
import java.util.Map;

public class BallTest {

    public static void main(String[] args) {
        Map<String, Map> prizeMap = new HashMap<>();
        Map<Integer, Integer> redPrizeMap = new HashMap<>();
        Map<Integer, Integer> bluePrizeMap = new HashMap<>();
        redPrizeMap.put(1, null);
        redPrizeMap.put(5, null);
        redPrizeMap.put(10, null);
        redPrizeMap.put(13, null);
        redPrizeMap.put(27, null);
        redPrizeMap.put(31, null);
        bluePrizeMap.put(7, null);
        prizeMap.put("RED", redPrizeMap);
        prizeMap.put("BLUE", bluePrizeMap);

        DoubleBallService start = new DoubleBallService();
        long startTime = System.currentTimeMillis();
        Map<String, Map> choseMap = start.randomBall(6, 1);
        start.printBall(start.randomBall(6, 1));
        System.out.println("");
        System.out.println("randomBall程序运行时间：" + (System.currentTimeMillis() - startTime) + "ms");

        PrizeBall prizeBall = WinGrade.checkWinBallNum(prizeMap, choseMap);
        System.out.println("红球命中数量：" + prizeBall.getRedHitNum() + "蓝球命中数量：" + prizeBall.getBlueHitNum());
        System.out.println("checkWinBallNum程序运行时间：" + (System.currentTimeMillis() - startTime) + "ms");

        WinGrade winGrade = WinGrade.checkWin(prizeBall);
        System.out.println("中奖等级为：" + winGrade.getPrize());
        System.out.println("checkWin程序运行时间：" + (System.currentTimeMillis() - startTime) + "ms");

    }
}
