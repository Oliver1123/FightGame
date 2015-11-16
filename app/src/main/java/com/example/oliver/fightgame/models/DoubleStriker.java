package com.example.oliver.fightgame.models;

/**
 * Created by oliver on 16.11.15.
 */
public class DoubleStriker extends Unit {
    // Critical Strike chance
    private int mCriticalStrike;

    public DoubleStriker(String name, int hp, int strength, int criticalStrike) {
        super(name, hp, strength);
        mCriticalStrike = criticalStrike;
    }

    @Override
    public void getDamage(int strength) {
        super.getDamage(strength);
    }

    @Override
    public void attackEnemy(Unit enemy) {
        super.attackEnemy(enemy);
        int hit = this.hit();
        enemy.getDamage((Unit.r.nextInt() % 100 < mCriticalStrike) ? 2 * hit : hit);
    }
}
