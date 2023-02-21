package com.zbc.practise.doubleball.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ball {
    // 球号码
    private int num;
    // 是否篮球
    private boolean isBlue = false;
}
