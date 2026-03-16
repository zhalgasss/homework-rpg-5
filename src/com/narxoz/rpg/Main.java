package com.narxoz.rpg;

import com.narxoz.rpg.decorator.*;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Homework 5 Demo: Decorator + Facade ===\n");

        // Create hero and boss
        HeroProfile hero = new HeroProfile("Arthas", 100);
        BossEnemy boss = new BossEnemy("Dragon", 120, 15);

        // Base attack
        AttackAction basic = new BasicAttack("Sword Strike", 10);

        // Decorator combinations
        AttackAction fire = new FireRuneDecorator(basic);
        AttackAction firePoison = new PoisonCoatingDecorator(fire);
        AttackAction fullCombo =
                new CriticalFocusDecorator(
                        new PoisonCoatingDecorator(
                                new FireRuneDecorator(basic)
                        )
                );

        System.out.println("--- Decorator Preview ---");

        printAction(basic);
        printAction(fire);
        printAction(firePoison);
        printAction(fullCombo);

        // Facade demo
        System.out.println("\n--- Facade Preview ---");

        DungeonFacade facade = new DungeonFacade().setRandomSeed(42L);

        AdventureResult result =
                facade.runAdventure(hero, boss, fullCombo);

        System.out.println("\nWinner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        System.out.println("Reward: " + result.getReward());

        System.out.println("\n--- Battle Log ---");
        for (String line : result.getLog()) {
            System.out.println(line);
        }

        System.out.println("\n=== Demo Complete ===");
    }

    private static void printAction(AttackAction action) {
        System.out.println("Action: " + action.getActionName());
        System.out.println("Damage: " + action.getDamage());
        System.out.println("Effects: " + action.getEffectSummary());
        System.out.println();
    }
}