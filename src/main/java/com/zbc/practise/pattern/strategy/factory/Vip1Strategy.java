package com.zbc.practise.pattern.strategy.factory;

import com.zbc.practise.pattern.strategy.Strategy;

public class Vip1Strategy implements Strategy {
    @Override
    public double addPoint(Double chrage) {
        return chrage * 0.8;
    }
}
