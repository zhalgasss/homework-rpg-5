package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

import java.util.Random;

public class BattleService {

    private Random random = new Random(1L);

    public BattleService setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {

        AdventureResult result = new AdventureResult();

        int heroHp = hero.getHealth();
        int bossHp = boss.getHealth();

        int rounds = 0;

        result.addLine("Battle started: " + hero.getName() + " vs " + boss.getName());

        while (heroHp > 0 && bossHp > 0 && rounds < 3) {

            rounds++;

            int heroDamage = action.getDamage();
            bossHp -= heroDamage;

            result.addLine("Round " + rounds + ": "
                    + hero.getName()
                    + " uses " + action.getActionName()
                    + " and deals "
                    + heroDamage + " damage");

            if (bossHp <= 0) {
                break;
            }

            int bossDamage = 5 + random.nextInt(6);
            heroHp -= bossDamage;

            result.addLine(boss.getName()
                    + " strikes back for "
                    + bossDamage + " damage");
        }

        result.setRounds(rounds);

        if (heroHp > 0 && bossHp <= 0) {
            result.setWinner(hero.getName());
        } else if (bossHp > 0 && heroHp <= 0) {
            result.setWinner(boss.getName());
        } else {
            result.setWinner("Draw");
        }

        return result;
    }
}