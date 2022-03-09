package com.zbc.practise.pattern.strategy;

/**
 * @author StormT1King
 * 回血的技能
 */
public class HealthSkill implements SkillStrategy {
    @Override
    public int damageNum(int ackDamage, int defHealth) {
        return ackDamage + defHealth;
    }
}
