package com.narxoz.rpg.facade;

public class RewardService {

    public String determineReward(AdventureResult battleResult) {

        if (battleResult == null) {
            throw new IllegalArgumentException("Battle result cannot be null");
        }

        String winner = battleResult.getWinner();

        if (winner == null) {
            return "No reward";
        }

        if (winner.equalsIgnoreCase("Draw")) {
            return "Small consolation reward: 10 gold";
        }

        if (winner.equalsIgnoreCase("Boss")) {
            return "No reward. The hero was defeated.";
        }

        return "Victory reward: 100 gold and epic item";
    }
}