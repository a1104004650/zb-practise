package com.zbc.practise.pattern.strategy;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @author StormT1King
 * 策略模式，满足Comparable
 */
@Data
public class Cat implements Comparable<Cat>{

    int age;
    int maxAge;

    @Override
    public int compareTo(@NotNull Cat c) {
        if(age < c.getAge()) {
            return -1;
        }else if(age > c.getAge()) {
            return 1;
        }
        return 0;
    }

    public Cat(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat age" + age;
    }
}
