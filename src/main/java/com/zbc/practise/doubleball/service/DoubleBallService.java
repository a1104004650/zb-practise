package com.zbc.practise.doubleball.service;

import com.zbc.practise.doubleball.dto.Ball;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author StormT1King
 * 结合实际生活业务场景写代码
 * 双色球业务类
 */
public class DoubleBallService {
    private Integer redMaxNum = 33;
    private Integer blueMaxNum = 16;
    private Map<String, Map> tmp = new HashMap<>();
    private Map<Integer, Boolean> tmpRedList = new HashMap<>();
    private Map<Integer, Boolean> tmpBlueList = new HashMap<>();
    private String RED = "RED";
    private String BLUE = "BLUE";

    // 机选
    public Map<String, Map> randomBall(int redNum, int blueNum) {
        // 机选红球
        for (; tmpRedList.size() < redNum;) {

            pushBall(tmpRedList, randomBallNum(false));

        }
        // 机选蓝球
        for (; tmpBlueList.size() < blueNum;) {

            pushBall(tmpBlueList, randomBallNum(true));

        }

        return pushTmp(tmpRedList, tmpBlueList);
    }

    public int randomBallNum(boolean isBlue) {
        if(isBlue) {
            return (int) (Math.random()*blueMaxNum+1);
        }
        return (int) (Math.random()*redMaxNum+1);
    }

    public void pushBall(Map<Integer, Boolean> tmpList,Integer ballNum) {
        if(!tmpList.containsKey(ballNum)){
            tmpList.put(ballNum, null);
        }
    }

    public Map<String, Map> pushTmp(Map<Integer, Boolean> red,Map<Integer, Boolean> blue) {
        tmp.clear();
        tmp.put(RED, red);
        tmp.put(BLUE, blue);
        return tmp;
    }

    public void printBall(Map<String, Map> tmp) {
        System.out.println("红球：");
        for (Object key: tmp.get(RED).keySet()) {
            System.out.print(key + ",");
        }
        System.out.println("");
        System.out.println("蓝球：");
        for (Object key: tmp.get(BLUE).keySet()) {
            System.out.print(key + ",");
        }
    }

    public Map<String, Map> buildBallMap(Integer[] redList,Integer[] blueList) {
        Map<String, Map> prizeMap = new HashMap<>();
        Map<Integer, Integer> redPrizeMap = new HashMap<>();
        Map<Integer, Integer> bluePrizeMap = new HashMap<>();

        for (int i = 0; i < redList.length; i++) {
            redPrizeMap.put(redList[i], null);
        }
        for (int i = 0; i < redList.length; i++) {
            bluePrizeMap.put(redList[i], null);
        }

        prizeMap.put("RED", redPrizeMap);
        prizeMap.put("BLUE", bluePrizeMap);
        return prizeMap;
    }



    
}
