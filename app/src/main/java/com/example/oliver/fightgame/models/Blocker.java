package com.example.oliver.fightgame.models;

import android.util.Log;

/**
 * Created by oliver on 16.11.15.
 *
 */
public class Blocker extends Unit {
//    Can block mBlock percent of damage
    private int mBlock;

    public Blocker(String name, int hp, int strength, int mana, int block) {
        super(name, hp, strength,mana);
        mBlock = block;
    }

    @Override
    public String getDamage(int damage) {
        String result = "";
        int blockedDamage = (int) (damage * (mBlock / (float) 100));
        result += getClass().getSimpleName() + " " +  getName() + " blocked " + blockedDamage + "damage! ";
        result += super.getDamage(damage - blockedDamage);
        return result;
    }

    @Override
    public String attackEnemy(Unit enemy) {
        return super.attackEnemy(enemy) + " " + enemy.getDamage(this.hit());
    }

    @Override
    public String counterAttack(Unit enemy) {
        return super.counterAttack(enemy) + " " + enemy.getDamage(this.hit() / 2);
    }

    @Override
    public String toString() {
        return super.toString() + ", block: " + mBlock + "%";
    }
}
