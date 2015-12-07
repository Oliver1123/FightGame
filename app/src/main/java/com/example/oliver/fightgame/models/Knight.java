package com.example.oliver.fightgame.models;

/**
 * Knight unit can block a damage equals to his armor.
 */
public class Knight extends Unit {
    private  int mArmor;

    public Knight(String _name, int _hp, int _strength, int _mana, int _armor) {
        super(_name, _hp, _strength, _mana);
        mArmor = _armor;
    }

    @Override
    public String getDamage(int _damage) {
       int realDamage = (_damage > mArmor) ? _damage - mArmor : 0;
        return super.getDamage(realDamage);
    }

    @Override
    public String attackEnemy(Unit _enemy) {
        return super.attackEnemy(_enemy) + " " + _enemy.getDamage(this.hit());
    }

    @Override
    public String counterAttack(Unit _enemy) {
        return super.counterAttack(_enemy) + " " + _enemy.getDamage(this.hit() / 2);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", armor: " + mArmor;
    }
}
