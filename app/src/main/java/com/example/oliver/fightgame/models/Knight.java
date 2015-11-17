package com.example.oliver.fightgame.models;

/**
 * Created by oliver on 16.11.15.
 * Warrior unit has armor
 */
public class Knight extends Unit {
    private  int mArmor;

    public Knight(String name, int hp, int strength, int mana, int armor) {
        super(name, hp, strength, mana);
        mArmor = armor;
    }

    @Override
    public String getDamage(int damage) {
       return super.getDamage(damage - mArmor);
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
        return super.toString() +
                ", armor: " + mArmor;
    }
}
