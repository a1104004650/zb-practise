package com.zbc.practise.pattern.strategy.factory;

import com.zbc.practise.pattern.strategy.Strategy;

public class Vip3Strategy implements Strategy {
    @Override
    public double addPoint(Double chrage) {
        return chrage;
    }
}
