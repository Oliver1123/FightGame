package com.example.oliver.fightgame.models;

/**
 * Blocker unit can block [blockPercent]% of damage.
 */
public class Blocker extends Unit {
//    Can block mBlockPercent percent of damage
    private int mBlockPercent;

    public Blocker(String _name, int _hp, int _strength, int _mana, int _blockPercent) {
        super(_name, _hp, _strength,_mana);
        mBlockPercent = _blockPercent;
    }

    @Override
    public String getDamage(int _damage) {
        String result = "";
        int blockedDamage = (int) (_damage * (mBlockPercent / (float) 100));
        result += getClass().getSimpleName() + " " +  getName() + " blocked " + blockedDamage + "damage! ";
        result += super.getDamage(_damage - blockedDamage);
        return result;
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
        return super.toString() + ", block: " + mBlockPercent + "%";
    }
}
