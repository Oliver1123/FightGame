package com.example.oliver.fightgame.models;

import android.util.Log;

import com.example.oliver.fightgame.RandomValue;

/**
 * Created by oliver on 16.11.15.
 */
public  class Unit {
    private String mName;
    private int mMaxHealth;
    private int mCurrentHealth;
    private int mStrength; // relate to hit power
    private int mMana; // relate to heal power
    private boolean isDead;


    public Unit(String name, int hp, int strength, int mana) {
        mName = name;
        mMaxHealth = hp;
        mCurrentHealth = hp;
        mStrength = strength;
        mMana = mana;
    }

    public String getDamage(int damage) {
        String result;
        if (this.mCurrentHealth > damage) {
            this.mCurrentHealth -= damage;
            result =  getClass().getSimpleName() + " " + mName + " lose " + damage + "hp, " + mCurrentHealth + "hp left. ";
        } else {
            result = getClass().getSimpleName() + " " + mName + " lose " + mCurrentHealth + "hp. " + mName + " is dead. ";
            this.mCurrentHealth = 0;
            this.isDead = true;
        }
       return result;
    }

    public String attackEnemy(Unit enemy){
        return  getClass().getSimpleName() + " " + mName + " attack " + enemy.getName()+ ". ";
    }

    public String counterAttack(Unit enemy){
        return  getClass().getSimpleName() + " " + mName + " counterattack " + enemy.getName()+ ". ";
    }

    /**
     * Heal  (mana +/- random[0; mana/4)) hp
     */
    public String heal() {
        int sign = (RandomValue.nextInt() % 2 == 0) ? 1 : -1;
        int divergence = RandomValue.nextInt((int) (mMana * 0.25));
        Log.d("tag", "heal divergence: " + divergence);
        return heal(mMana + sign * divergence);
    }

    public String heal(int health) {
        int healHP = (mCurrentHealth + health < mMaxHealth) ? health : mMaxHealth - mCurrentHealth;
        mCurrentHealth += healHP;
        return getClass().getSimpleName() + " " + mName+  " heal " + healHP + "hp, " + mCurrentHealth + "hp left. ";
    }

    public boolean isDead() {
        return isDead;
    }
    public String getName() {
        return mName;
    }

    /** Calculate hit power = strength +/- random[0; strength/4)
     *
     * @return hit strength
     */
    public int hit() {
        int sign = (RandomValue.nextInt() % 2 == 0) ? 1 : -1;
        int divergence = RandomValue.nextInt((int) (mStrength * 0.25));
        Log.d("tag", "hit divergence: " + divergence);
        return (mStrength + sign * divergence);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unit)) return false;

        Unit unit = (Unit) o;

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
