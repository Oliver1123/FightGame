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

    public BladeMaster(String name, int hp, int strength, int criticalStrike) {
        super(name, hp, strength);
        mCriticalStrike = criticalStrike;
    }

    @Override
    public void getDamage(int strength) {
        super.getDamage(strength);
    }

    @Override
    public void attackEnemy(Unit enemy) {
        int hit = this.hit();
        boolean doubleStrike = RandomValue.nextInt() % 100 < mCriticalStrike;
        if (doubleStrike) {
            Log.d("tag", "Double Strike!");
            enemy.getDamage(2 * hit);
        } else {
            enemy.getDamage(hit);
        }
        super.attackEnemy(enemy);
    }

    @Override
    public String toString() {
        return super.toString() + ", criticalStrike: " + mCriticalStrike + "%";
    }
}
