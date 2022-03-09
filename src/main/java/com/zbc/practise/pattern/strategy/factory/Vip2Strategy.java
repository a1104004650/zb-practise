package com.zbc.practise.pattern.strategy.factory;

import com.zbc.practise.pattern.strategy.Strategy;

/**
 * @author StormT1King
 */
public class Vip2Strategy implements Strategy {
    @Override
    public double addPoint(Double chrage) {
        return chrage * 0.9;
    }
}
