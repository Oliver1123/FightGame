package com.example.oliver.fightgame.models;

import com.example.oliver.fightgame.RandomValue;

/**
 * Ninja unit has a chance to completely avoid attack.
 */
public class Ninja extends Unit {
    // chance to evasion enemy attack
    private int mEvasionChance;

    public Ninja(String _name, int _hp, int _strength, int _mana, int _evasion) {
        super(_name, _hp, _strength, _mana);
        mEvasionChance = _evasion;
    }


    @Override
    public String getDamage(int _damage) {
        // Calculate if unit can avoid strike
        boolean avoidAttack = (RandomValue.nextInt() % 100 < mEvasionChance);
        return  avoidAttack ? getClass().getSimpleName() + " " + getName() + " avoid attack! " :
                              super.getDamage(_damage);
    }

    @Override
    public String attackEnemy(Unit _enemy) {
        return  super.attackEnemy(_enemy) + " " + _enemy.getDamage(this.hit());
    }

    @Override
    public String counterAttack(Unit _enemy) {
        return super.counterAttack(_enemy) + " " + _enemy.getDamage(this.hit() / 2);
    }

    @Override
    public String toString() {
        return super.toString() + ", evasion: " + mEvasionChance + "%";
    }
}
