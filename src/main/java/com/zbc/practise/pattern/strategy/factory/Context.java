package com.zbc.practise.pattern.strategy.factory;

import com.zbc.practise.pattern.strategy.Strategy;
import lombok.Data;

/**
 * @author StormT1King
 */
@Data
public class Context {
    private Strategy strategy;

    public Double addPoint(Double charge, String type) {
        strategy = StrategyFactory.getInstance().creator(type);
        return strategy.addPoint(charge);
    }

}
