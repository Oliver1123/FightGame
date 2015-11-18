package com.example.oliver.fightgame.models;

import android.util.Log;

import com.example.oliver.fightgame.RandomValue;

/**
 * Created by oliver on 16.11.15.
 * has a double critical strike
 */
public class BladeMaster extends Unit {
    // Critical Strike chance
    private int mCriticalStrike;

    public BladeMaster(String name, int hp, int strength, int mana, int criticalStrike) {
        super(name, hp, strength, mana);
        mCriticalStrike = criticalStrike;
    }

    @Override
    public String  getDamage(int damage) {
        return super.getDamage(damage);
    }

    @Override
    public String attackEnemy(Unit enemy) {
        String result =  super.attackEnemy(enemy);
        int hit = this.hit();
        boolean doubleStrike = RandomValue.nextInt() % 100 < mCriticalStrike;
        if (doubleStrike) {
            result += " Double Strike! ";
            result += enemy.getDamage(2 * hit);
        } else {
            result += enemy.getDamage(hit);
        }
       return result;
    }

    @Override
    public String counterAttack(Unit enemy) {
        return super.counterAttack(enemy) + " " + enemy.getDamage(hit() / 2);
    }

    @Override
    public String toString() {
        return super.toString() + ", criticalStrike: " + mCriticalStrike + "%";
    }
}
