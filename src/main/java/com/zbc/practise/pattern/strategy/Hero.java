package com.zbc.practise.pattern.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author StormT1King
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hero {

    private SkillStrategy skill;

    public int executeSkill(int ackDamage, int defHealth){
        return skill.damageNum(ackDamage, defHealth);
    }
}
