package com.zbc.practise.pattern.strategy;

/**
 * @author StormT1King
 * 输出技能
 */
public class DamageSkill implements SkillStrategy {
    @Override
    public int damageNum(int ackDamage, int defHealth) {
        return ackDamage - defHealth;
    }
}
