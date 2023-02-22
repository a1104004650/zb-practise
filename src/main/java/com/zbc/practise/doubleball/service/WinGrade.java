package com.zbc.practise.doubleball.service;


import com.zbc.practise.doubleball.dto.PrizeBall;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author StormT1King
 */

public enum WinGrade {

    NONE_PRIZE("未中奖", Collections.unmodifiableMap(new HashMap<Integer, Integer>() {
        {
            put(0, 0);
        }
    })),
    SIXTH_PRIZE("六等奖", Collections.unmodifiableMap(new HashMap<Integer, Integer>() {
        {
            put(0, 1);
            put(1, 1);
            put(2, 1);
        }
    })),
    FIFTH_PRIZE("五等奖", Collections.unmodifiableMap(new HashMap<Integer, Integer>() {
        {
            put(4, 0);
            put(3, 1);
        }
    })),
    FOURTH_PRIZE("四等奖", Collections.unmodifiableMap(new HashMap<Integer, Integer>() {
        {
            put(5, 0);
            put(4, 1);
        }
    })),
    THIRD_PRIZE("三等奖", Collections.unmodifiableMap(new HashMap<Integer, Integer>() {
        {
            put(5, 1);
        }
    })),
    SECOND_PRIZE("二等奖", Collections.unmodifiableMap(new HashMap<Integer, Integer>() {
        {
            put(6, 0);
        }
    })),
    FIRST_PRIZE("一等奖", Collections.unmodifiableMap(new HashMap<Integer, Integer>() {
        {
            put(6, 1);
        }
    }))
    ;
    private String prize;
    private Map<Integer,Integer> prizeMap;

    public String getPrize() {
        return prize;
    }

    public Map<Integer,Integer> getPrizeMap() {
        return prizeMap;
    }

    WinGrade(String prize, Map<Integer, Integer> prizeMap) {
        this.prize = prize;
        this.prizeMap = prizeMap;
    }

    public static WinGrade checkWin(PrizeBall prize) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        if(prize.getRedHitNum() == null){
            prize.setRedHitNum(0);
        }

        if(prize.getBlueHitNum() == null){
            prize.setBlueHitNum(0);
        }
        map.put(prize.getRedHitNum(), prize.getBlueHitNum());
        for(WinGrade w : WinGrade.values()) {
            WinGrade checkGrade = checkWinGrade(w, map);
            if(checkGrade != null){
                return checkGrade;
            }
        }
        return WinGrade.NONE_PRIZE;
    }

    // 验证是否属于该等级奖
    private static WinGrade checkWinGrade(WinGrade grade, Map<Integer, Integer> map) {
        for (Integer key: grade.getPrizeMap().keySet()) {
            // 如果属于该奖池的规则，那么就返回该奖池的等级
            if(map.get(key) != null && map.get(key).equals(grade.getPrizeMap().get(key))){
                return grade;
            }
        }
        return null;
    }

    // 检查中奖数量
    public static PrizeBall checkWinBallNum(Map<String, Map> prizeMap, Map<String, Map> choseMap) {
        // 查看红球中的数量
        Integer redHitNum = 0;
        Integer blueHitNum = 0;
        for (Object key: choseMap.get("RED").keySet()) {
            if(prizeMap.get(key) != null) {
                redHitNum++;
            }
        }

        // 查看蓝球中的数量
        for (Object key: choseMap.get("BLUE").keySet()) {
            if(prizeMap.get(key) != null) {
                blueHitNum++;
            }
        }

        PrizeBall prize = new PrizeBall();
        prize.setBlueHitNum(blueHitNum);
        prize.setRedHitNum(redHitNum);
      return prize;
    }
}
