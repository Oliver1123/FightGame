package com.example.oliver.fightgame.models;

import android.util.Log;

import com.example.oliver.fightgame.RandomValue;

/**
 * Created by oliver on 16.11.15.
 */
public class Ninja extends Unit {
    // chance to evasion enemy attack
    private int mEvasionChance;
    public Ninja(String name, int hp, int strength, int evasion) {
        super(name, hp, strength);
        mEvasionChance = evasion;
    }


    @Override
    public void getDamage(int strength) {
        // Calculate if unit can avoid strike
        int evasion = (RandomValue.nextInt() % 100 < mEvasionChance) ? 0 : 1;
        if (evasion == 0 )
            Log.d("tag", getName() + " avoid attack!");
        super.getDamage(strength * evasion);
    }

    @Override
    public void attackEnemy(Unit enemy) {
        super.attackEnemy(enemy);
        enemy.getDamage(this.hit());
    }

    @Override
    public String toString() {
        return super.toString() + ", evasion: " + mEvasionChance + "%";
    }
}
