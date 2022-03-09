package com.zbc.practise.pattern.strategy;

import com.zbc.practise.pattern.strategy.factory.Context;
import com.zbc.practise.pattern.strategy.factory.VipTypeEnum;

import java.util.Arrays;

/**
 * @author StormT1King
 * 策略模式 Comparable
 * 开闭原则，对拓展开放，对修改关闭（尽量不要修改）。
 * 易于扩展，增加一个新的策略只需要添加一个具体的策略类即可，基本不需要改变原有的代码，符合开放封闭原则
 * 避免使用多重条件选择语句，充分体现面向对象设计思想
 * 策略类之间可以自由切换，由于策略类都实现同一个接口，所以使它们之间可以自由切换
 * 客户端与策略算法解耦，两者都依赖于抽象策略接口，符合依赖反转原则
 */
public class StrategyDemo {

    public static void main(String[] args) {
        int[] a = {9, 2, 3, 7, 8};
        // 这个排序仅仅只能对int排序，后续没有扩展
        Sorter.sort(a);
        System.out.println(Arrays.toString(a));

        // 使用Comparable解决上述问题，每一个需要排序的类只需要实现compareto方法即可
        Cat[] cats = new Cat[] {new Cat(4),new Cat(2),new Cat(3)};
        Sorter2.sort(cats);
        System.out.println(Arrays.toString(cats));

        // 建立一个策略类 就跟Comparable一样。
        Hero hero = new Hero(new DamageSkill());
        System.out.println(hero.executeSkill(1,4));

        // 不同的策略新建不同的实例
        Hero hero2 = new Hero();
        hero2.setSkill(new HealthSkill());
        System.out.println(hero2.executeSkill(1,4));

        // 工厂模式 + 单例模式 + 策略模式
        Context context = new Context();
        // 根据不同的VIP类型算出不同的分数。
        System.out.println(context.addPoint((double)100, VipTypeEnum.VIP1.getCode()));
        // 但是这样破坏了开闭原则。。
        System.out.println(context.addPoint((double)100, VipTypeEnum.VIP3.getCode()));

    }

}
