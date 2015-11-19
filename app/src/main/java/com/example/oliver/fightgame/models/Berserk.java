package com.example.oliver.fightgame.models;

/**
 * Berserk unit that make double damage but also get double damage.
 */

public class Berserk extends Unit {
    public Berserk(String _name, int _hp, int _strength, int _mana) {
        super(_name, _hp, _strength, _mana);
    }

    @Override
    public String getDamage(int _damage) {
        return super.getDamage(2 * _damage);
    }

    @Override
    public String  attackEnemy(Unit _enemy) {
        return super.attackEnemy(_enemy) + " " + _enemy.getDamage(2 * this.hit());
    }

    @Override
    public String counterAttack(Unit _enemy) {
        return super.counterAttack(_enemy) + " " + _enemy.getDamage(this.hit());
    }
}
