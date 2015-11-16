package com.example.oliver.fightgame.models;

/**
 * Created by oliver on 16.11.15.
 * Unit that make double damage but also get double damage
 */

public class Berserk extends Unit {
    public Berserk(String name, int hp, int strength) {
        super(name, hp, strength);
    }

    @Override
    public void getDamage(int strength) {
        super.getDamage(2 * strength);
    }

    @Override
    public void attackEnemy(Unit enemy) {
       super.attackEnemy(enemy);
        enemy.getDamage(2 * this.hit());
    }
}
