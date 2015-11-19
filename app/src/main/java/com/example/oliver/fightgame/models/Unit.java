package com.example.oliver.fightgame.models;

import com.example.oliver.fightgame.RandomValue;

/**
 * Unit class is abstract entity that represent a fighter with basic characteristics name, hp, strength and  mana.
 * Unit can perform such kinds of actions as get damage, heal, hit etc.
 */
public abstract class Unit {
    private String mName;
    private int mMaxHealth;
    private int mCurrentHealth;
    private int mStrength; // relate to hit power
    private int mMana;     // relate to regenerate power
    private boolean isDead;

    public Unit(String _name, int _hp, int _strength, int _mana) {
        mName = _name;
        mMaxHealth = _hp;
        mCurrentHealth = _hp;
        mStrength = _strength;
        mMana = _mana;
    }

    /**
     * Simulate getting this unit a damage
     * @return unit state after getting {@param _damage} points of damage.
     */
    public String getDamage(int _damage) {
        if (isDead) return getClass().getSimpleName() + " " + mName + " is dead. ";

        String result;
        if (this.mCurrentHealth > _damage) {
            this.mCurrentHealth -= _damage;
            result =  getClass().getSimpleName() + " " + mName + " lose " + _damage + "hp, " + mCurrentHealth + "hp left. ";
        } else {
            result = getClass().getSimpleName() + " " + mName + " lose " + mCurrentHealth + "hp. " + mName + " is DEAD. ";
            this.mCurrentHealth = 0;
            this.isDead = true;
        }
       return result;
    }

    /**
     * Deals damage to _enemy.
     * @return information about attack
     */
    public String attackEnemy(Unit _enemy){
        return  getClass().getSimpleName() + " " + mName + " attack " + _enemy.getName() + ". ";
    }


    /**
     * @return information about counterattack
     */
    public String counterAttack(Unit _enemy){
        return  getClass().getSimpleName() + " " + mName + " counterattack " + _enemy.getName()+ ". ";
    }

    /**
     * Regenerate (mana +/- random[0; mana/4)) hp points
     * @return information about process
     */
    public String regenerate() {
        if (isDead) return getClass().getSimpleName() + " " + mName + " is dead. ";

        int sign = (RandomValue.nextInt() % 2 == 0) ? 1 : -1;
        int divergence = RandomValue.nextInt((int) (mMana * 0.25));
        return heal(mMana + sign * divergence);
    }

    /**
     * Heal _health points of unit health
     * @param _health
     * @return information about process
     */
    protected final String heal(int _health) {
        int healHP = (mCurrentHealth + _health < mMaxHealth) ? _health : mMaxHealth - mCurrentHealth;
        mCurrentHealth += healHP;
        return getClass().getSimpleName() + " " + mName+  " heal " + healHP + "hp, " + mCurrentHealth + "hp left. ";
    }

    public final boolean isDead() {
        return isDead;
    }
    public final String getName() {
        return mName;
    }
    public final int getHP() {
        return mCurrentHealth;
    }
    public final int getMaxHP() {
        return mMaxHealth;
    }

    /**
     * Calculate hit power = strength +/- random[0; strength/4)
     *
     * @return hit value
     */
    public final int hit() {
        int sign = (RandomValue.nextInt() % 2 == 0) ? 1 : -1;
        int divergence = RandomValue.nextInt((int) (mStrength * 0.25));
        return (mStrength + sign * divergence);
    }

    @Override
    public boolean equals(Object _o) {
        if (this == _o) return true;
        if (!(_o instanceof Unit)) return false;

        Unit unit = (Unit) _o;

        if (mMaxHealth != unit.mMaxHealth) return false;
        if (mCurrentHealth != unit.mCurrentHealth) return false;
        if (mStrength != unit.mStrength) return false;
        if (isDead != unit.isDead) return false;
        return mName.equals(unit.mName);

    }

    @Override
    public int hashCode() {
        int result = mName.hashCode();
        result = 31 * result + mMaxHealth;
        result = 31 * result + mCurrentHealth;
        result = 31 * result + mStrength;
        result = 31 * result + (isDead ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " name: '" + mName + '\'' +
                ", hp: " + mCurrentHealth +
                ", strength: " + mStrength +
                ", mana: " + mMana;
    }
}
