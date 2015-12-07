package com.example.oliver.fightgame.models;

import android.util.Pair;

import com.example.oliver.fightgame.global.RandomValue;

/**
 * Unit class is abstract entity that represent a fighter with basic characteristics name, hp, strength, mana, range and position.
 * Unit can perform such kinds of actions as get damage, heal, hit, move etc.
 */
public abstract class Unit {
    public static final int DIRECTION_UP    = 0;
    public static final int DIRECTION_DOWN  = 1;
    public static final int DIRECTION_LEFT  = 2;
    public static final int DIRECTION_RIGHT = 3;

    private String mName;
    private int mMaxHealth;
    private int mCurrentHealth;
    private int mStrength;      // relate to hit power
    private int mMana;          // relate to regenerate power
    private int mRange = 1;     // distance where unit can attack enemy, default value 1
    private boolean isDead;
    private Pair<Integer, Integer> mPosition;

    public Unit(String _name, int _hp, int _strength, int _mana) {
        mName = _name;
        mMaxHealth = _hp;
        mCurrentHealth = _hp;
        mStrength = _strength;
        mMana = _mana;
    }

    public Pair<Integer, Integer> getPosition() {
        return mPosition;
    }

    public void setPosition(Pair<Integer, Integer> _unitPosition) {
        mPosition = _unitPosition;
    }

    /**
     * @return distance where unit can attack enemy, default value 1
     */
    public int getRange() {
        return mRange;
    }

    /**
     * Set unit given value as unit range
     * @throws IllegalArgumentException
     *                if value < 1
     */
    public void setRange(int _range) {
        if (_range < 1)
            throw new IllegalArgumentException("Range must be positive");
        mRange = _range;
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
    public final int getStrength() {
        return mStrength;
    }
    public final int getMana() {
        return mMana;
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

    /**
     * @return true if enemy is close enough to attack, false otherwise
     */
    public boolean canAttackEnemy(Unit _enemy) {
        return Math.round(getDistance(getPosition(), _enemy.getPosition())) <= mRange;
    }

    /**
     * Move unit to specified direction
     */
    public void move(int _direction){
        Pair<Integer, Integer> newPosition;
        switch (_direction) {
            case DIRECTION_UP:
                newPosition = new Pair<>(mPosition.first - 1, mPosition.second);
                break;
            case DIRECTION_DOWN:
                newPosition = new Pair<>(mPosition.first + 1, mPosition.second);
                break;
            case DIRECTION_LEFT:
                newPosition = new Pair<>(mPosition.first, mPosition.second - 1);
                break;
            case DIRECTION_RIGHT:
                newPosition = new Pair<>(mPosition.first, mPosition.second + 1);
                break;
            default:
                throw new IllegalArgumentException("Direction value must be one of defined direction constants");

        }
        mPosition = newPosition;
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

    private double getDistance(Pair<Integer, Integer> _pointA, Pair<Integer, Integer> _pointB) {
        return Math.sqrt(Math.pow((_pointB.first - _pointA.first), 2) +
                         Math.pow((_pointB.second - _pointA.second), 2));
    }
}
