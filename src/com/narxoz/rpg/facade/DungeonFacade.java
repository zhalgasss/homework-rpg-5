package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class DungeonFacade {

    private final PreparationService preparationService = new PreparationService();
    private final BattleService battleService = new BattleService();
    private final RewardService rewardService = new RewardService();

    public DungeonFacade setRandomSeed(long seed) {
        battleService.setRandomSeed(seed);
        return this;
    }

    public AdventureResult runAdventure(HeroProfile hero, BossEnemy boss, AttackAction action) {

        // 1. preparation
        String preparationSummary = preparationService.prepare(hero, boss, action);

        // 2. battle
        AdventureResult result = battleService.battle(hero, boss, action);

        // add preparation info
        result.addLine(preparationSummary);

        // 3. reward
        result.setReward(rewardService.determineReward(result));

        return result;
    }
}