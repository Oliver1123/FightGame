package com.example.oliver.fightgame.models;

/**
 * Created by oliver on 16.11.15.
 * unit can block [blockPercent]% of damage
 */
public class Blocker extends Unit {
//    Can block mBlockPercent percent of damage
    private int mBlockPercent;

    public Blocker(String name, int hp, int strength, int mana, int blockPercent) {
        super(name, hp, strength,mana);
        mBlockPercent = blockPercent;
    }

    @Override
    public String getDamage(int damage) {
        String result = "";
        int blockedDamage = (int) (damage * (mBlockPercent / (float) 100));
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
        return super.toString() + ", block: " + mBlockPercent + "%";
    }
}
