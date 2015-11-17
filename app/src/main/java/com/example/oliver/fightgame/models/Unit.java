package com.example.oliver.fightgame.models;

import android.util.Log;
import android.util.Pair;

import com.example.oliver.fightgame.RandomValue;

import java.util.Random;

/**
 * Created by oliver on 16.11.15.
 */
public  class Unit {
    private String mName;
    private int mMaxHealth;
    private int mCurrentHealth;
    private int mStrength;
    private boolean isDead;


    public Unit(String name, int hp, int strength) {
        mName = name;
        mMaxHealth = hp;
        mCurrentHealth = hp;
        mStrength = strength;
    }
    public void getDamage(int strength) {
        if (this.mCurrentHealth > strength) {
            this.mCurrentHealth -= strength;
            Log.d("tag", getClass().getSimpleName() + " " + mName + " lose " + strength + "hp, " + mCurrentHealth + "hp left.");
        } else {
            Log.d("tag", getClass().getSimpleName() + " " + mName + " lose " + mCurrentHealth + "hp, 0hp left. " + mName + " is dead.");
            this.mCurrentHealth = 0;
            this.isDead = true;
        }

    }

    public void attackEnemy(Unit enemy){
        Log.d("tag", getClass().getSimpleName() +" " + mName + " attack " + enemy.getName());
    }

    /**
     * Heal 0%-10% from max health
     */
    public void heal() {
        int health = RandomValue.nextInt((int) (mMaxHealth * 0.1));
        heal(health);
    }

    public void heal(int health) {
        if (mCurrentHealth + health < mMaxHealth) {
            Log.d("tag", getClass().getSimpleName() + " heal " + health + "hp.");
        } else {
            Log.d("tag", getClass().getSimpleName() + " heal " + (mMaxHealth - mCurrentHealth) + "hp.");
            mCurrentHealth = mMaxHealth;
        }
    }

    public int getHP(){
        return mCurrentHealth;
    }

    public int getStrength() {
        return mStrength;
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
        int divergence = RandomValue.nextInt((int) (getStrength() * 0.25));
        return (getStrength() + sign * divergence);
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
                ", maxHP: " + mMaxHealth +
                ", hp: " + mCurrentHealth +
                ", strength: " + mStrength;
    }
}
