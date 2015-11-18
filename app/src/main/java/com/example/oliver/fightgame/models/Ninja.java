package com.example.oliver.fightgame.models;

import com.example.oliver.fightgame.RandomValue;

/**
 * Created by oliver on 16.11.15.
 */
public class Ninja extends Unit {
    // chance to evasion enemy attack
    private int mEvasionChance;
    public Ninja(String name, int hp, int strength, int mana, int evasion) {
        super(name, hp, strength, mana);
        mEvasionChance = evasion;
    }


    @Override
    public String getDamage(int damage) {
        // Calculate if unit can avoid strike
        boolean avoidAttack = (RandomValue.nextInt() % 100 < mEvasionChance);
        return  avoidAttack ? getClass().getSimpleName() + " " + getName() + " avoid attack! " :
                              super.getDamage(damage);
    }

    @Override
    public String attackEnemy(Unit enemy) {
        return  super.attackEnemy(enemy) + " " + enemy.getDamage(this.hit());
    }

    @Override
    public String counterAttack(Unit enemy) {
        return super.counterAttack(enemy) + " " + enemy.getDamage(this.hit() / 2);
    }

    @Override
    public String toString() {
        return super.toString() + ", evasion: " + mEvasionChance + "%";
    }
}
