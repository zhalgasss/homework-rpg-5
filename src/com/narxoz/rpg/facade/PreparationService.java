package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class PreparationService {

    public String prepare(HeroProfile hero, BossEnemy boss, AttackAction action) {

        if (hero == null) {
            throw new IllegalArgumentException("Hero cannot be null");
        }

        if (boss == null) {
            throw new IllegalArgumentException("Boss cannot be null");
        }

        if (action == null) {
            throw new IllegalArgumentException("Attack action cannot be null");
        }

        return "Preparation complete: Hero " + hero.getName()
                + " is ready to fight " + boss.getName()
                + " using action [" + action.getActionName() + "]";
    }
}