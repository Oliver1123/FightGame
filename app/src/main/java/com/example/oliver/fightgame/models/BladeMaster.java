package com.example.oliver.fightgame.models;

import com.example.oliver.fightgame.RandomValue;

/**
 * BladeMaster unit has a chance make double critical strike.
 */
public class BladeMaster extends Unit {
    // Critical Strike chance
    private int mCriticalStrikeChance;

    public BladeMaster(String _name, int _hp, int _strength, int _mana, int _criticalStrikeChance) {
        super(_name, _hp, _strength, _mana);
        mCriticalStrikeChance = _criticalStrikeChance;
    }

    @Override
    public String  getDamage(int _damage) {
        return super.getDamage(_damage);
    }

    @Override
    public String attackEnemy(Unit _enemy) {
        String result =  super.attackEnemy(_enemy);
        int hit = this.hit();
        boolean doubleStrike = RandomValue.nextInt() % 100 < mCriticalStrikeChance;
        if (doubleStrike) {
            result += "Double Strike! " + _enemy.getDamage(2 * hit);
        } else {
            result += _enemy.getDamage(hit);
        }
       return result;
    }

    @Override
    public String counterAttack(Unit _enemy) {
        return super.counterAttack(_enemy) + " " + _enemy.getDamage(hit() / 2);
    }

    @Override
    public String toString() {
        return super.toString() + ", criticalStrike: " + mCriticalStrikeChance + "%";
    }
}
