package com.example.oliver.fightgame.models;

/**
 * Created by oliver on 16.11.15.
 * Warrior unit has armor
 */
public class Knight extends Unit {
    private  int mArmor;

    public Knight(String name, int hp, int strength, int armor) {
        super(name, hp, strength);
        mArmor = armor;
    }

    @Override
    public void getDamage(int strength) {
        super.getDamage(strength - mArmor);
    }

    @Override
    public void attackEnemy(Unit enemy) {
        super.attackEnemy(enemy);
        enemy.getDamage(this.hit());
    }

    @Override
    public String toString() {
        return super.toString() +
                ", armor: " + mArmor;
    }
}
