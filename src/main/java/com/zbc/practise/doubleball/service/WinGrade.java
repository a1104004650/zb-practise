package com.zbc.practise.doubleball.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author StormT1King
 */

public enum WinGrade {
    FIRST_PRIZE("一等奖", Collections.unmodifiableMap(new HashMap<Integer, Integer>() {
        {
            put(6, 1);
        }
    })),
    SECOND_PRIZE("二等奖", Collections.unmodifiableMap(new HashMap<Integer, Integer>() {
        {
            put(6, 0);
        }
    })),
    THIRD_PRIZE("三等奖", Collections.unmodifiableMap(new HashMap<Integer, Integer>() {
        {
            put(5, 1);
        }
    })),
    FOURTH_PRIZE("四等奖", Collections.unmodifiableMap(new HashMap<Integer, Integer>() {
        {
            put(5, 0);
            put(4, 1);
        }
    })),
    FIFTH_PRIZE("五等奖", Collections.unmodifiableMap(new HashMap<Integer, Integer>() {
        {
            put(4, 0);
            put(3, 1);
        }
    })),
    SIXTH_PRIZE("六等奖", Collections.unmodifiableMap(new HashMap<Integer, Integer>() {
        {
            put(0, 1);
            put(1, 1);
            put(2, 1);
        }
    }));

    <K, V> WinGrade(String first_prize, Map<K,V> unmodifiableMap) {
    }
}
