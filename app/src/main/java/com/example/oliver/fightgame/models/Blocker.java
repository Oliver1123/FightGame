package com.example.oliver.fightgame.models;

import android.util.Log;

/**
 * Created by oliver on 16.11.15.
 *
 */
public class Blocker extends Unit {
//    Can block mBlock percent of damage
    private int mBlock;

    public Blocker(String name, int hp, int strength, int block) {
        super(name, hp, strength);
        mBlock = block;
    }

    @Override
    public void getDamage(int strength) {
        int blockedDamage = strength / 100 * mBlock;
        Log.d("tag", getName() + " blocked " + blockedDamage + "damage!");
        super.getDamage(strength - blockedDamage);
    }

    @Override
    public void attackEnemy(Unit enemy) {
        super.attackEnemy(enemy);
        enemy.getDamage(this.hit());
    }

    @Override
    public String toString() {
        return super.toString() + ", block: " + mBlock + "%";
    }
}
