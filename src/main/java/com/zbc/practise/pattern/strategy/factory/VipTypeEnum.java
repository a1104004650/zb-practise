package com.zbc.practise.pattern.strategy.factory;

import lombok.Getter;

/**
 * @author StormT1King
 */
@Getter
public enum VipTypeEnum {
    VIP1("01","VIP1"),
    VIP2("02","VIP2"),
    VIP3("03","VIP3");

    private String code;
    private String value;

    VipTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
