package com.example.oliver.fightgame.models;

/**
 * Created by oliver on 16.11.15.
 * Unit that make double damage but also get double damage
 */

public class Berserk extends Unit {
    public Berserk(String name, int hp, int strength, int mana) {
        super(name, hp, strength, mana);
    }

    @Override
    public String getDamage(int damage) {
        return super.getDamage(2 * damage);
    }

    @Override
    public String  attackEnemy(Unit enemy) {
        return super.attackEnemy(enemy) + " " + enemy.getDamage(2 * this.hit());
    }

    @Override
    public String counterAttack(Unit enemy) {
        return super.counterAttack(enemy) + " " + enemy.getDamage(this.hit());
    }
}
