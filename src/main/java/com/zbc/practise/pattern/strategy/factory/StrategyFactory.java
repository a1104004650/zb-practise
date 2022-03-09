package com.zbc.practise.pattern.strategy.factory;

import com.zbc.practise.pattern.strategy.Strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author StormT1King
 */
public class StrategyFactory {

    private static StrategyFactory factory = new StrategyFactory();
    private StrategyFactory(){
    }

    private static Map strategyMap = new HashMap<>();
    static{
        strategyMap.put(VipTypeEnum.VIP1.getCode(), new Vip1Strategy());
        strategyMap.put(VipTypeEnum.VIP2.getCode(), new Vip2Strategy());
        strategyMap.put(VipTypeEnum.VIP3.getCode(), new Vip3Strategy());
    }

    // 单例模式
    public static StrategyFactory getInstance(){
        return factory;
    }

    // 工厂模式
    public Strategy creator(String type){
        return (Strategy) strategyMap.get(type);
    }


}
