package com.zbc.practise.doubleball.test;

import com.zbc.practise.doubleball.dto.PrizeBall;
import com.zbc.practise.doubleball.service.DoubleBallService;
import com.zbc.practise.doubleball.service.WinGrade;

import java.util.HashMap;
import java.util.Map;

public class BallTest {

    public static void main(String[] args) {
        // 中奖号码
        long startTime = System.currentTimeMillis();
        DoubleBallService start = new DoubleBallService();
        Integer[] redList = new Integer[]{2,7,12,13,22,27};
        Integer[] blueList = new Integer[]{9};
        // 中奖集合
        Map<String, Map> prizeMap = start.buildBallMap(redList, blueList);
        // 机选集合
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
